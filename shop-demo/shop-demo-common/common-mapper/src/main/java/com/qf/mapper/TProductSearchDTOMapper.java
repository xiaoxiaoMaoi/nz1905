package com.qf.mapper;

import com.qf.dto.TProductSearchDTO;

import java.util.List;


public interface TProductSearchDTOMapper {

    List<TProductSearchDTO> selectAll();

    TProductSearchDTO selectById(Long id);
}
