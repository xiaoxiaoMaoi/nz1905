package com.qf.shop.demo.regist.consumer.service.fallback;

import com.qf.shop.demo.regist.consumer.service.RegistService;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceHystrix implements RegistService {
    @Override
    public String sayHi(String message) {
        return String.format("当前是登录注册服务：您的网络有问题!!!");
    }
}
