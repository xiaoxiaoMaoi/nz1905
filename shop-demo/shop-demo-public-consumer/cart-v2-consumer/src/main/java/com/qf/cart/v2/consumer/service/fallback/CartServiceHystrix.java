package com.qf.cart.v2.consumer.service.fallback;

import com.qf.cart.v2.consumer.service.ICartService;
import com.qf.dto.ResultBean;
import org.springframework.stereotype.Component;

@Component
public class CartServiceHystrix implements ICartService {

    @Override
    public ResultBean addProductToCart(String id, Long productId, int count) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public ResultBean cleanCart(String uuid) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public ResultBean updateCart(String uuid, Long productId, int count) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public ResultBean showCart(String id) {
        return ResultBean.error("抱歉，您的网络有问题");
    }

    @Override
    public ResultBean mergeCart(String uuid, String userId) {
        return ResultBean.error("抱歉，您的网络有问题");
    }
}
