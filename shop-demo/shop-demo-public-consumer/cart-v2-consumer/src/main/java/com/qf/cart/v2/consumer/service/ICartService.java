package com.qf.cart.v2.consumer.service;

import com.qf.cart.v2.consumer.service.fallback.CartServiceHystrix;
import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cart-v2-service",fallback = CartServiceHystrix.class)
public interface ICartService {

    @RequestMapping("cart/add")
    ResultBean addProductToCart(@RequestParam(value = "id")String id, @RequestParam(value = "productId")Long productId, @RequestParam(value = "count")int count);

    @RequestMapping("cart/clean")
    ResultBean cleanCart(@RequestParam(value = "uuid")String uuid);

    @RequestMapping("cart/update")
    ResultBean updateCart(@RequestParam(value = "uuid")String uuid, @RequestParam(value = "productId")Long productId, @RequestParam(value = "count")int count);

    @RequestMapping("cart/show")
    ResultBean showCart(@RequestParam(value = "id")String id);

    @RequestMapping("cart/merge")
    ResultBean mergeCart(@RequestParam(value = "uuid")String uuid, @RequestParam(value = "userId")String userId);
}
