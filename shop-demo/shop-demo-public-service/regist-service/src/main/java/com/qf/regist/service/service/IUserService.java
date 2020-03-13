package com.qf.regist.service.service;/*
    @auter wwx
    @date 2020/3/11
*/

import com.qf.dto.ResultBean;

public interface IUserService {

    ResultBean checkLogin(String username, String password);

    ResultBean checkIsLogin(String uuid);
}
