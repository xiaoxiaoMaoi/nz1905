package com.qf.regist.service.service.impl;/*
    @auter wwx
    @date 2020/3/10
*/

import com.qf.dto.ResultBean;
import com.qf.entity.TUser;
import com.qf.mapper.UserMapper;
import com.qf.regist.service.service.IRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistServiceImpl implements IRegistService {

    @Autowired
    UserMapper mapper;

    @Override
    public ResultBean regist(String uname, String password) {
        TUser user = new TUser();
        user.setUname(uname);
        user.setPassword(password);
        mapper.insert(user);
        return ResultBean.success("注册成功");
    }
}