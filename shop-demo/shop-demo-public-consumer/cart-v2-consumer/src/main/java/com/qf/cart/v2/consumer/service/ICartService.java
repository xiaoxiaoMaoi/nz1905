package com.qf.cart.v2.consumer.service;

import com.qf.cart.v2.consumer.service.fallback.CartServiceHystrix;
import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "cart-v2-service",fallback = CartServiceHystrix.class)
public interface ICartService {

    @RequestMapping("cart/add")
    ResultBean addProductToCart(String id, Long productId, int count);

    @RequestMapping("cart/clean")
    ResultBean cleanCart(String uuid);

    @RequestMapping("cart/update")
    ResultBean updateCart(String uuid, Long productId, int count);

    @RequestMapping("cart/show")
    ResultBean showCart(String id);

    @RequestMapping("cart/merge")
    ResultBean mergeCart(String uuid, String userId);
}
