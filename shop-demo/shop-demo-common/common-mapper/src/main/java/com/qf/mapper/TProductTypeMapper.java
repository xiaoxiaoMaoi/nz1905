package com.qf.mapper;

import com.qf.entity.TProductType;
import tk.mybatis.mapper.common.Mapper;

public interface TProductTypeMapper extends Mapper<TProductType> {
    int deleteByPrimaryKey(Long cid);

    int insert(TProductType record);

    int insertSelective(TProductType record);

    TProductType selectByPrimaryKey(Long cid);

    int updateByPrimaryKeySelective(TProductType record);

    int updateByPrimaryKey(TProductType record);
    //以上请忽略
}
