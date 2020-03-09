package com.qf.shop.demo.regist.consumer.controller;

import com.qf.shop.demo.regist.consumer.service.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistController {

    @Autowired
    private RegistService service;

    @RequestMapping("hi")
    @ResponseBody
    public String sayHi(@RequestParam String message){
        return service.sayHi(message);
    }

}
