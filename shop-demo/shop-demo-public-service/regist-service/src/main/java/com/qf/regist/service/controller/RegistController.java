package com.qf.regist.service.controller;/*
    @auter wwx
    @date 2020/3/10
*/

import com.qf.dto.ResultBean;
import com.qf.regist.service.service.IRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class RegistController {


    @Autowired
    private IRegistService registService;

    @RequestMapping("regist/{uname}/{password}")
    public ResultBean regist(@PathVariable String uname, @PathVariable String password){
        return registService.regist(uname,password);
    }


}
