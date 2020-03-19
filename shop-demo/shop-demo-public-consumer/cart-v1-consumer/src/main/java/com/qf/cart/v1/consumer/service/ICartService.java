package com.qf.cart.v1.consumer.service;/*
    @auter wwx
    @date 2020/3/17
*/

import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cart-v1-service")
public interface  ICartService {
    @RequestMapping("cart/addProduct")
    ResultBean addProduct(@RequestParam("id") String id, @RequestParam("productId") Long productId, @RequestParam("count") int count);

    @RequestMapping("cart/clean")
    ResultBean clean(@RequestParam("uuid") String uuid);

    @RequestMapping("cart/update")
    ResultBean update(@RequestParam("uuid") String uuid, @RequestParam("productId") Long productId, @RequestParam("count") int count);

    @RequestMapping("cart/showCart")
    ResultBean showCart(@RequestParam("id") String id);

    @RequestMapping("cart/merge")
    ResultBean merge(@RequestParam("uuid") String uuid, @RequestParam("userId") String userId);
}
