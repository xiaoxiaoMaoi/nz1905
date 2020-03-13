package com.qf.regist.consumer.service;/*
    @auter wwx
    @date 2020/3/11
*/

import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("regist-service")
public interface  IUserService {
    @RequestMapping("user/checkLogin")
    ResultBean checkLogin(@RequestParam("username")  String username, @RequestParam("password")  String password);

    @RequestMapping("user/checkIsLogin")
    ResultBean checkIsLogin(@RequestParam("uuid")  String uuid);
}
