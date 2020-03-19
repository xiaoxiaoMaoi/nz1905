package com.qf.back.v2.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.back.v2.service.service.IBackService;
import com.qf.dto.PageBean;
import com.qf.dto.ResultBean;
import com.qf.entity.TOrder;
import com.qf.entity.TProduct;
import com.qf.entity.TUser;
import com.qf.mapper.TOrderMapper;
import com.qf.mapper.TProductMapper;
import com.qf.mapper.TProductTypeMapper;
import com.qf.mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BackServiceImpl implements IBackService {

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private TOrderMapper orderMapper;

    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private TProductTypeMapper productTypeMapper;

    @Override
    public ResultBean login(String username, String password) {
        TUser user = userMapper.checkLogin(username,password);
        if (user != null) {
            return ResultBean.success(user,"登录成功！");
        }
        return ResultBean.error("登录失败！");
    }

    //-------------以下是订单的操作------------------

    @Override
    public PageInfo<TOrder> orderList(PageBean pageBean,String account) {
        PageHelper.startPage(pageBean.getCurrentPage(),pageBean.getPageSize());
//        Example example = new Example(TOrder.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("account",account);
        List<TOrder> orders =orderMapper.selectOrderList(account);
        System.out.println(orders.get(0).getCreatedate().getClass().getName().toString());
        return new PageInfo<>(orders);
    }

    @Override
    public ResultBean updateOrderInit(Integer id) {
        TOrder order = orderMapper.selectByPrimaryKey(id);
        if (order != null) {
            return ResultBean.success(order,"修改初始化成功!");
        }
        return ResultBean.success("修改初始化失败!");
    }

    @Override
    public ResultBean updateOrderById(TOrder order) {
        int result = orderMapper.updateByPrimaryKeySelective(order);
        if (result != 0) {
            return ResultBean.success("修改订单成功！");
        }
        return ResultBean.error("修改订单失败");
    }

    @Override
    public ResultBean addOrder(TOrder order) {
        int result = orderMapper.insertSelective(order);
        if (result!=0) {
            return ResultBean.success("添加订单成功!");
        }
        return ResultBean.error("添加订单失败！");
    }

    @Override
    public ResultBean deleteOrderById(Integer id) {
        int result = orderMapper.updateOrderByDel(id);
        if (result != 0) {
            return ResultBean.success("取消订单成功！");
        }
        return ResultBean.success("取消订单失败！");
    }

    //-------------以下是商品的操作------------------

    @Override
    public PageInfo<TProduct> productList(PageBean pageBean,String pname,Integer typeId) {
        PageHelper.startPage(pageBean.getCurrentPage(),pageBean.getPageSize());
//        Example example = new Example(TProduct.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("pname",pname);
        List<TProduct> products =productMapper.selectProductList(pname,typeId);
        return new PageInfo<>(products);
    }

    @Override
    public ResultBean updateProductInit(Integer id) {
        TProduct product =  productMapper.selectByPrimaryKey(id);
        if (product != null) {
            return ResultBean.success(product,"初始化商品成功!");
        }
        return ResultBean.error("初始化商品失败！");
    }

    @Override
    public ResultBean updateProductById(TProduct tProduct) {
        tProduct.setUpdateTime(new Date());
        int result =  productMapper.updateByPrimaryKeySelective(tProduct);
        if (result != 0) {
            return ResultBean.success("修改商品成功!");
        }
        return ResultBean.error("修改商品失败！");
    }

    @Override
    public ResultBean addProduct(TProduct product) {
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        int result = productMapper.insertSelective(product);
        if (result != 0) {
            return ResultBean.success("添加商品成功!");
        }
        return ResultBean.error("添加商品失败！");
    }

    @Override
    public ResultBean addProductInit(Integer id) {
        TUser user = userMapper.selectByPrimaryKey(id);
        if (user != null) {
            return ResultBean.success(user,"添加商品成功!");
        }
        return ResultBean.error("添加商品失败！");
    }

    @Override
    public ResultBean deleteProductById(Integer id) {
        int result = productMapper.deleteByPrimaryKey(id);
        if (result != 0) {
            return ResultBean.success("删除商品成功！");
        }
        return ResultBean.success("删除商品失败！");
    }

    @Override
    public ResultBean selectProductType() {
        return ResultBean.success(productTypeMapper.selectAll(),"获取类型成功");
    }

}
