package com.qf.mapper;

import com.qf.entity.TProductDesc;
import tk.mybatis.mapper.common.Mapper;

public interface ProductDescMapper extends Mapper<TProductDesc> {
    int deleteByPrimaryKey(Long id);

    int insert(TProductDesc record);

    int insertSelective(TProductDesc record);

    TProductDesc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TProductDesc record);

    int updateByPrimaryKeyWithBLOBs(TProductDesc record);

    int updateByPrimaryKey(TProductDesc record);
    //以上请忽略
}