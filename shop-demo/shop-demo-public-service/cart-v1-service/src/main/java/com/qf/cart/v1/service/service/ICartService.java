package com.qf.cart.v1.service.service;/*
    @auter wwx
    @date 2020/3/17
*/

import com.qf.dto.ResultBean;

public interface ICartService {


    ResultBean addProduct(String id, Long productId, int count);

    ResultBean clean(String uuid);

    ResultBean update(String uuid, Long productId, int count);

    ResultBean showCart(String id);

    ResultBean merge(String uuid, String userId);
}