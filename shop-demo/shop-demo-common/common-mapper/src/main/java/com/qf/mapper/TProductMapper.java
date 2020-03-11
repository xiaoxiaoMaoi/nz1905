package com.qf.mapper;

import com.qf.entity.TProduct;
import tk.mybatis.mapper.common.Mapper;

public interface TProductMapper extends Mapper<TProduct> {
    int deleteByPrimaryKey(Long pid);

    int insert(TProduct record);

    int insertSelective(TProduct record);

    TProduct selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(TProduct record);

    int updateByPrimaryKey(TProduct record);
    //以上请忽略
}
