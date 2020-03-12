package com.qf.mapper;

import com.qf.dto.TProductDTO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Ray.Cheng
 * @Date 2020/3/11
 */
public interface TProductDTOMapper extends Mapper<TProductDTO> {
    List<TProductDTO> getAll();
}
