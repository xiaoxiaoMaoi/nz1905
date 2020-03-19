package com.qf.cart.v2.service.service.impl;

import com.qf.cart.v2.service.service.ICartService;
import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.dto.TProductCartDTO;
import com.qf.entity.TProduct;
import com.qf.mapper.TProductMapper;
import com.qf.util.StringUtil;
import com.qf.vo.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TProductMapper productMapper;

    //--------------分隔线---------------
    //添加商品到购物车中
    @Override
    public ResultBean addProductToCart(String id, Long productId, int count) {
        /*
         *     * 	1）当前用户没有购物车
         *      * 		新建购物车，把商品添加到购物车中，再把购物车存到redis中
         *      * 	2）当前用户有购物车，但是购物车中没有该商品
         *      * 		先从redis中获取该购物车，再把商品添加都购物车中，再存入到redis中。
         *      * 	3）当前用户有购物车，且购物车中有该商品
         *      * 		先从redis中获取该购物车，再获取该商品的数量，再让新的数量和老的数量相加，更新回购物车中，再更新回redis中。
         */

        String redisKey = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE,id);

        //第一种情况
        Object o = redisTemplate.opsForValue().get(redisKey);
        if (o != null) {
            //当前用户没有购物车
            //封装一个购物车对象给对象，并存入到redis中
            CartItem cartItem = new CartItem();
            cartItem.setProductId(productId);
            cartItem.setCount(count);
            cartItem.setUpdateTime(new Date());

            //存入到购物车中
            List<CartItem> carts = new ArrayList<>();
            carts.add(cartItem);
            //存入到redis中
            redisTemplate.opsForValue().set(redisKey,carts);
            return ResultBean.success(carts,"添加购物车成功！");
        }

        //-------------分隔线------------
        //第2 或 第3 种情况
        List<CartItem> carts = (List<CartItem>) o;
        for (CartItem cartItem : carts) {
            if (cartItem.getProductId().longValue() == productId.longValue()) {
                //当前用户有购物车，且购物车中有该商品
                cartItem.setCount(cartItem.getCount()+count);
                //更新商品的时间：为最后添加的该商品的时间
                cartItem.setUpdateTime(new Date());
                //购物车中的商品已更新，然后把购物车存入redis中
                redisTemplate.opsForValue().set(redisKey,carts);
                return ResultBean.success(carts,"添加购物车成功");
            }
        }

        //-------------分隔线------------
        //当前用户有购物车，但购物车中没有该商品
        //封装购物车商品对象
        CartItem  cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setCount(count);
        cartItem.setUpdateTime(new Date());
        carts.add(cartItem);
        //把购物车存放到redis中
        redisTemplate.opsForValue().set(redisKey,carts);
        return ResultBean.success(carts,"添加购物车成功");
    }

    //--------------分隔线---------------
    //清空购物车
    @Override
    public ResultBean cleanCart(String uuid) {
        String redisKey = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE,uuid);
        redisTemplate.delete(redisKey);
        return ResultBean.success("清空购物车成功");
    }

    //--------------分隔线---------------
    //更新购物车
    @Override
    public ResultBean updateCart(String uuid, Long productId, int count) {
        if (uuid != null && !"".equals(uuid)){
            //组织redis中的键
            String redisKey = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE,uuid);
            Object o = redisTemplate.opsForValue().get(redisKey);
            if (o != null) {
                List<CartItem> carts = (List<CartItem>) o;
                for (CartItem cartItem : carts) {
                    if (cartItem.getProductId().longValue() == productId.longValue()) {
                        cartItem.setCount(count);
                        cartItem.setUpdateTime(new Date());
                        //把集合直接存回到redis中
                        redisTemplate.opsForValue().set(redisKey,carts);
                        return ResultBean.success(carts,"更新购物车成功");
                    }
                }
            }
        }
        return ResultBean.error("当前用户没有购物车");
    }

    //--------------分隔线---------------
    //查看购物车
    @Override
    public ResultBean showCart(String id) {// user:cart:userId     userId   排序
        if (id != null && !"".equals(id)){
            String redisKey = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE,id);
            Object o = redisTemplate.opsForValue().get(redisKey);
            if (o != null) {
                List<CartItem> carts = (List<CartItem>) o;
                List<TProductCartDTO> products = new ArrayList<>();
                for (CartItem cartItem : carts) {
                    //去redis中取
                    String productKey = StringUtil.getRedisKey(RedisConstant.PRODUCT_PRE,cartItem.getProductId().toString());
                    TProduct pro = (TProduct) redisTemplate.opsForValue().get(productKey);
                    if (pro == null) {
                        //去数据库拿商品信息，再存redis
                        pro = productMapper.selectByPrimaryKey(cartItem.getProductId());
                        //存redis
                        redisTemplate.opsForValue().set(productKey,pro);
                    }
                    //pro已经存在
                    TProductCartDTO cartDTO = new TProductCartDTO();

                    //封装
                    cartDTO.setProduct(pro);
                    cartDTO.setCount(cartItem.getCount());
                    cartDTO.setUpdateTime(cartItem.getUpdateTime());

                    //存到product集合中
                    products.add(cartDTO);
                }

                //对集合中的元素进行排序，Comparator用来指明排序依据
                Collections.sort(products, new Comparator<TProductCartDTO>() {
                    @Override
                    public int compare(TProductCartDTO o1, TProductCartDTO o2) {
                        //这是2个Long值，谁大谁排在前面
                        return (int)(o1.getUpdateTime().getTime()-o2.getUpdateTime().getTime());
                    }
                });
                return ResultBean.success(products);
            }
        }
        return ResultBean.error("当前用户没有购物车");
    }

    //--------------分隔线---------------
    //合并购物车
    @Override
    public ResultBean mergeCart(String uuid, String userId) {
        /*
        合并
        1.未登录状态下没有购物车==》合并成功
        2.未登录状态下有购物车，但已登录状态下没有购物车==》把未登录的变成已登录的
        3.未登录状态下有购物车，但已登录状态下也有购物车，而且购物车中的商品有重复==》难点！
         */
        //获得两种
        String noLoginRedisKey = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE,uuid);
        String loginRedisKey = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE,userId);
        Object noLoginO = redisTemplate.opsForValue().get(noLoginRedisKey);
        Object loginO = redisTemplate.opsForValue().get(loginRedisKey);

        //1.未登录状态下没有购物车存在，直接用用户自己的购物车
        if (noLoginO == null) {
            return ResultBean.success("未登录状态下没有购物车，不需要合并");
        }

        //2.未登录状态下有购物车，但已登录状态下没有购物车，把未登录的购物车编程已登录的购物车
        if (loginO == null) {
            redisTemplate.opsForValue().set(noLoginRedisKey,noLoginO);
            //删除未登录状态下的购物车
            redisTemplate.delete(noLoginRedisKey);
            return ResultBean.success(noLoginO,"合并成功");
        }

        //3.未登录状态下有购物车，但已登录下也有购物车，而且购物车中的商品有重复
        List<CartItem> noLoginCarts = (List<CartItem>) noLoginO;
        List<CartItem> loginCarts = (List<CartItem>) loginO;
        //先创建一个Map
        Map<Long,CartItem> map = new HashMap<>();
        for (CartItem noLoginCartItem : noLoginCarts) {
            map.put(noLoginCartItem.getProductId(),noLoginCartItem);
        }
        //此时map中就有所有的未登录状态下的购物车中的商品
        //存入已登录状态下购物车的商品
        for (CartItem loginCartItem : loginCarts) {
            //尝试去检查下map中该商品是否已存在
            CartItem currentCartItem = map.get(loginCartItem.getProductId());
            if (currentCartItem != null) {
                //已存在
                currentCartItem.setCount(currentCartItem.getCount()+loginCartItem.getCount());
                //时间 必然是未登录状态下添加的更近，所以不需要操作
            }else {
                //不存在，直接放
                map.put(loginCartItem.getProductId(),loginCartItem);
            }
        }
        //此时Map中存放的数据就是合并之后的购物车
        //删除未登录状态下的购物车
        redisTemplate.delete(noLoginRedisKey);
        //把新的购物车存入到redis中
        Collection<CartItem> values = map.values();
        List<CartItem> newCarts = new ArrayList<>(values);
        redisTemplate.opsForValue().set(loginRedisKey,newCarts);
        return ResultBean.success(newCarts,"合并成功");
    }
}
