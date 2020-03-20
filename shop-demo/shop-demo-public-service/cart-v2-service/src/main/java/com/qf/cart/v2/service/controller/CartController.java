package com.qf.cart.v2.service.controller;

import com.qf.cart.v2.service.service.ICartService;
import com.qf.dto.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private ICartService service;

    @RequestMapping("add")
    public ResultBean addProductToCart(String id, Long productId, int count){
        return service.addProductToCart(id,productId,count);
    };

    @RequestMapping("clean")
    public ResultBean cleanCart(String uuid){
        return service.cleanCart(uuid);
    };

    @RequestMapping("update")
    public ResultBean updateCart(String uuid, Long productId, int count){
        return service.updateCart(uuid,productId,count);
    };

    @RequestMapping("show")
    public ResultBean showCart(String id){
        return service.showCart(id);
    };

    @RequestMapping("merge")
    public ResultBean mergeCart(String uuid, String userId){
        return service.mergeCart(uuid,userId);
    };
}
