package com.qf.mapper;

import com.qf.entity.TProduct;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface TProductMapper extends Mapper<TProduct> {

    List<TProduct> selectProductList(@Param("pname") String pname, @Param("typeId")Integer typeId);
}
