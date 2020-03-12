package com.qf.mapper;

import com.qf.entity.TOrder;
import tk.mybatis.mapper.common.Mapper;

public interface TOrderMapper extends Mapper<TOrder> {
    int updateOrderByDel(Integer id);
}