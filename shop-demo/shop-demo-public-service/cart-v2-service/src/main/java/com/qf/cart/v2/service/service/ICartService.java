package com.qf.cart.v2.service.service;

import com.qf.dto.ResultBean;

public interface ICartService {

    ResultBean addProductToCart(String id,Long productId,int count);

    ResultBean cleanCart(String uuid);

    ResultBean updateCart(String uuid,Long productId,int count);

    ResultBean showCart(String id);

    ResultBean mergeCart(String uuid,String userId);
}
