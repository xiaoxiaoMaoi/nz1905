package com.qf.back.v2.service.service.impl;

import com.qf.back.v2.service.service.IBackService;
import com.qf.dto.ResultBean;
import com.qf.entity.TOrder;
import com.qf.entity.TProduct;
import com.qf.mapper.TOrderMapper;
import com.qf.mapper.TProductMapper;
import com.qf.mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackServiceImpl implements IBackService {

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private TOrderMapper orderMapper;

    @Autowired
    private TProductMapper productMapper;

    @Override
    public ResultBean login(String username, String password) {

        return null;
    }

    //-------------以下是订单的操作------------------

    @Override
    public ResultBean orderList(String account) {
        return null;
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
    public ResultBean productList(String name) {
        return null;
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
        int result =  productMapper.updateByPrimaryKeySelective(tProduct);
        if (result != 0) {
            return ResultBean.success("修改商品成功!");
        }
        return ResultBean.error("修改商品失败！");
    }

    @Override
    public ResultBean addProduct(TProduct product) {
        int result = productMapper.insertSelective(product);
        if (result != 0) {
            return ResultBean.success("添加商品成功!");
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

}
