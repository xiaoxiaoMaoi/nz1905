package com.qf.mapper;

import com.qf.entity.TProduct;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface TProductMapper extends Mapper<TProduct> {

    List<TProduct> selectProductList(String pname, Integer typeId);
}
