package com.qf.shop.demo.shoppingcon.summer.service;

import com.qf.shop.demo.shoppingcon.summer.service.fallback.AdminServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "shop-demo-shopping-service",fallback = AdminServiceHystrix.class)
public interface ShoppingService {

    @RequestMapping("hi")
    String sayHi(@RequestParam String message);

}
