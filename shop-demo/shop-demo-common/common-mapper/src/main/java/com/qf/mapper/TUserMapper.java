package com.qf.mapper;

import com.qf.entity.TUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TUserMapper extends Mapper<TUser> {

    TUser checkLogin(@Param("username") String username,@Param("password")String password);

    TUser selectByUsername(String username);
}
