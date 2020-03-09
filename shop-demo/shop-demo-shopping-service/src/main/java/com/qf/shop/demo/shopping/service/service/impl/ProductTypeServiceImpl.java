package com.qf.shop.demo.shopping.service.service.impl;

import com.qf.entity.TProductType;
import com.qf.mapper.ProductTypeMapper;
import com.qf.shop.demo.shopping.service.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements IProductTypeService {

    @Autowired
    private ProductTypeMapper mapper;

    @Override
    public List<TProductType> getList() {
        return mapper.selectAll();
    }
}
