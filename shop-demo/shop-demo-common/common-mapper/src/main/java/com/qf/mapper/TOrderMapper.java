package com.qf.mapper;

import com.qf.entity.TOrder;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TOrderMapper extends Mapper<TOrder> {
    int updateOrderByDel(Integer id);

    List<TOrder> selectOrderList(String account);
}