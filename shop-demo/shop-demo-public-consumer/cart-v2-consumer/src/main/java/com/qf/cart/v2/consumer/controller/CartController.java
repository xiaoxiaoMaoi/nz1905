package com.qf.cart.v2.consumer.controller;

import com.qf.cart.v2.consumer.service.ICartService;
import com.qf.constant.CookieConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("add")
    @ResponseBody
    public ResultBean addProductToCart(@CookieValue(name = CookieConstant.USER_CART, required = false) String uuid,
                                       @PathVariable Long productId,
                                       @PathVariable int count,
                                       HttpServletResponse response,
                                       HttpServletRequest request){
        Object o = request.getAttribute("user");
        if (o != null) {
            //-----------已登录状态下的购物车-----------
            TUser tUser = (TUser) o;
            Long userId = tUser.getId();
            return cartService.addProductToCart(userId.toString(),productId,count);
        }

        //---------未登录状态下的购物车--------
        //把该商品添加到购物车中，这个购物车是在redis中

        if (uuid==null||"".equals(uuid)){
            //uuid为空的话，生成一个uuid放到cookie里给客户端
            uuid = UUID.randomUUID().toString();
            //返回该uuid给cookie
            Cookie cookie = new Cookie(CookieConstant.USER_CART,uuid);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        ResultBean resultBean = cartService.addProductToCart(uuid,productId,count);
        return resultBean;
    }

    /**
     * 清空购物车
     * @param uuid
     * @param response
     * @return
     */
    @RequestMapping("clean")
    @ResponseBody
    public ResultBean cleanCart(@CookieValue(name=CookieConstant.USER_CART,required = false)String uuid,HttpServletResponse response,
                                HttpServletRequest request){
        Object o = request.getAttribute("user");
        if (o != null) {
            //---------已登录状态下的购物车--------
            TUser user = (TUser) o;
            return cartService.cleanCart(user.getId().toString());
        }
        //----------未登录状态下的购物车-----------
        if (uuid != null && !"".equals(uuid)) {
            //删除cookie
            Cookie cookie = new Cookie(CookieConstant.USER_CART,"");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);

            //删除redis中的购物车
            return cartService.cleanCart(uuid);
        }
        return ResultBean.error("当前用户没有购物车");
    }

    /**
     * 更新购物车中的商品
     * @param productId
     * @param count
     * @param uuid
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultBean updateCart(
            @PathVariable Long productId,
            @PathVariable int count,
            @CookieValue(name=CookieConstant.USER_CART,required = false)String uuid,
            HttpServletRequest request){

        Object o = request.getAttribute("user");
        if (o != null) {
            //------已登录状态下的购物车-----   user:cart:userId
            TUser user = (TUser) o ;
            return cartService.updateCart(user.getId().toString(),productId,count);
        }
        return cartService.updateCart(uuid,productId,count);
    }

    /**
     * 展示购物车
     * @param uuid
     * @param request
     * @return
     */
    @RequestMapping("show")
    @ResponseBody
    public ResultBean showCart(@CookieValue(name=CookieConstant.USER_CART,required = false)String uuid,HttpServletRequest request){

        Object o = request.getAttribute("user");
        //------先查看已登录状态下的购物车----------
        if (o != null) {
            TUser user = (TUser) o ;
            Long userId = user.getId();
            return cartService.showCart(userId.toString());
        }

        //-------查看未登录状态下的购物车------------
        return cartService.showCart(uuid);
    }

    /**
     * 合并两种状态下的购物车
     * @param uuid
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("merge")
    @ResponseBody
    public ResultBean mergeCart(@CookieValue(name = CookieConstant.USER_CART,required = false)String uuid,
                            HttpServletRequest request,HttpServletResponse response) {
        //获得uuid和uid
        TUser user = (TUser) request.getAttribute("user");
        String userId = null;
        if (user != null) {
            userId = user.getId().toString();
        }

        //做完合并以后，要把未登录状态下的购物车清空，再清空cookie
        Cookie cookie = new Cookie(CookieConstant.USER_CART,"");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return cartService.mergeCart(uuid,userId);
    }
}
