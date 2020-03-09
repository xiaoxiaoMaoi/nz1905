package com.qf.shop.demo.shopping.service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShoppingController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("hi")
    @ResponseBody
    public String sayHi(String message) {
        return String.format("当前是来自于%s的消息：%s", serverPort, message);
    }


}
