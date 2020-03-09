package com.qf.shop.demo.shoppingcon.summer.service.fallback;

import com.qf.shop.demo.shoppingcon.summer.service.ShoppingService;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceHystrix implements ShoppingService {
    @Override
    public String sayHi(String message) {
        return String.format("当前是购物的服务：您的网络有问题!!!");
    }
}
