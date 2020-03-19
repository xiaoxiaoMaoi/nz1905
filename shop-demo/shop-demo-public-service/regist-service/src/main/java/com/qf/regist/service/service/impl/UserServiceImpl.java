package com.qf.regist.service.service.impl;/*
    @auter wwx
    @date 2020/3/11
*/

import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.TUser;
import com.qf.mapper.TUserMapper;

import com.qf.regist.service.service.IUserService;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    TUserMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public ResultBean checkLogin(String username, String password){
        TUser user = mapper.selectByUsername(username);
            if(user!=null){
            if(password!=null&&!"".equals(password)&&encoder.matches(password,user.getPassword())){
                //这里使用spring security
                return ResultBean.success(user,"登录成功");

            }
        }
        return ResultBean.error("用户名或密码错误");
    }


    @Override
    public ResultBean checkIsLogin(String uuid){
        if(uuid!=null&&!"".equals(uuid)){
            //1.组织键   user:login:dbe06afc-8540-4b74-8035-99e188d33933
            String redisKey = StringUtil.getRedisKey(RedisConstant.USER_LOGIN_PRE, uuid);
            //2.去redis中查
            Object o = redisTemplate.opsForValue().get(redisKey);
            if(o!=null){
                TUser user  = (TUser) o;
                user.setPassword("");//数据传递时不带密码
                return ResultBean.success(user,"用户已登录");
            }
        }
        return ResultBean.error("用户未登录");
    }
}
