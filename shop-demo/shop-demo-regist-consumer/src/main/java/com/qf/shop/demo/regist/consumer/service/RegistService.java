package com.qf.shop.demo.regist.consumer.service;

import com.qf.shop.demo.regist.consumer.service.fallback.AdminServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "shop-demo-regist-service",fallback = AdminServiceHystrix.class)
public interface RegistService {

    @RequestMapping("hi")
    String sayHi(@RequestParam String message);

}
