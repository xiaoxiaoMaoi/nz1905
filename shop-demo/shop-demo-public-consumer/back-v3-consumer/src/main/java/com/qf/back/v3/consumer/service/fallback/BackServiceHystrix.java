package com.qf.back.v3.consumer.service.fallback;

import com.github.pagehelper.PageInfo;
import com.qf.back.v3.consumer.service.IBackService;
import com.qf.dto.PageBean;
import com.qf.dto.ResultBean;
import com.qf.entity.TOrder;
import com.qf.entity.TProduct;
import org.springframework.stereotype.Component;

@Component
public class BackServiceHystrix implements IBackService {
    @Override
    public ResultBean login(String username, String password) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public PageInfo<TOrder> orderList(PageBean pageBean, String account) {
        return null;
    }

    @Override
    public ResultBean updateOrderInit(Integer id) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public ResultBean updateOrderById(TOrder order) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public ResultBean addOrder(TOrder order) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public ResultBean deleteOrderById(Integer id) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public PageInfo<TProduct> productList(PageBean pageBean,String pname,Integer typeId) {
        return null;
    }

    @Override
    public ResultBean updateProductInit(Integer id) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public ResultBean updateProductById(TProduct tProduct) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public ResultBean addProduct(TProduct product) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public ResultBean addProductInit(Integer id) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public ResultBean deleteProductById(Integer id) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public ResultBean selectProductType() {
        return ResultBean.error("抱歉，您的网络有问题");
    }
}
