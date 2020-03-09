package com.qf.shop.demo.shopping.service.service.impl;

import com.qf.entity.TProduct;
import com.qf.mapper.ProductMapper;
import com.qf.shop.demo.shopping.service.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<TProduct> getList() {
        return mapper.selectAll();
    }
}
