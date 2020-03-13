package com.qf.back.v2.consumer.service;

import com.qf.dto.ResultBean;
import com.qf.entity.TOrder;
import com.qf.entity.TProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "back-v2-service")
public interface IBackService {

    @RequestMapping("back/login")
    ResultBean login(String username, String password);

    //-------------以下是订单的操作------------------

    @RequestMapping("bkack/orderList")
    ResultBean orderList(String account);

    @RequestMapping("back/updateOrderInit")
    ResultBean updateOrderInit(Integer id);

    @RequestMapping("back/updateOrderById")
    ResultBean updateOrderById(TOrder order);

    @RequestMapping("back/addOrder")
    ResultBean addOrder(TOrder order);

    @RequestMapping("back/deleteOrderById")
    ResultBean deleteOrderById(Integer id);

    //-------------以下是商品的操作------------------

    @RequestMapping("back/productList")
    ResultBean productList(String name);

    @RequestMapping("back/updateProductInit")
    ResultBean updateProductInit(Integer id);

    @RequestMapping("back/updateProductById")
    ResultBean updateProductById(TProduct tProduct);

    @RequestMapping("back/addProduct")
    ResultBean addProduct(TProduct product);

    @RequestMapping("back/deleteProductById")
    ResultBean deleteProductById(Integer id);
}
