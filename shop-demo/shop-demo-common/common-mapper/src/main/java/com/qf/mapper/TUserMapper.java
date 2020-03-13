package com.qf.mapper;

import com.qf.entity.TUser;
import tk.mybatis.mapper.common.Mapper;

public interface TUserMapper extends Mapper<TUser> {

    TUser selectByUsername(String username);
}
