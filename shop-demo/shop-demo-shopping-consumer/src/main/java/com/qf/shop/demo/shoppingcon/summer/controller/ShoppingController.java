package com.qf.shop.demo.shoppingcon.summer.controller;

import com.qf.shop.demo.shoppingcon.summer.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShoppingController {

    @Autowired
    private ShoppingService service;

    @RequestMapping("hi")
    @ResponseBody
    public String sayHi(@RequestParam String message){
        return service.sayHi(message);
    }

}
