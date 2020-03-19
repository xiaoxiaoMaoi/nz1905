package com.qf.back.v2.consumer.service;

import com.qf.back.v2.consumer.service.fallback.BackServiceHystrix;
import com.qf.dto.PageBean;
import com.qf.dto.ResultBean;
import com.qf.entity.TOrder;
import com.qf.entity.TProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "back-v2-service",fallback = BackServiceHystrix.class)
public interface IBackService {

    @RequestMapping("back/login")
    ResultBean login(@RequestParam(value = "username")String username, @RequestParam(value = "password")String password);

    //-------------以下是订单的操作------------------

    @RequestMapping("back/orderList")
    ResultBean orderList(@RequestParam(value = "pageBean")PageBean pageBean, @RequestParam(value = "account")String account);

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
    ResultBean productList(@RequestParam(value = "pageBean")PageBean pageBean,@RequestParam(value = "pname")String pname,@RequestParam(value = "typeId")Integer typeId);

    @RequestMapping("back/updateProductInit")
    ResultBean updateProductInit(Integer id);

    @RequestMapping("back/updateProductById")
    ResultBean updateProductById(TProduct tProduct);

    @RequestMapping("back/addProduct")
    ResultBean addProduct(TProduct product);

    @RequestMapping("back/addProductInit")
    ResultBean addProductInit(Integer id);

    @RequestMapping("back/deleteProductById")
    ResultBean deleteProductById(Integer id);

    @RequestMapping("back/productType")
    ResultBean selectProductType();
}
