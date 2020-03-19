package com.qf.order.v1.service.controller;

import com.qf.bean.Order;
import com.qf.bean.Orderdetail;
import com.qf.order.v1.service.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Ray.Cheng
 * @Date 2020/3/19
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @RequestMapping("/add")
    public int createOrder(@RequestBody Order order, @RequestParam List<Orderdetail> orderdetailList){
        return orderService.createOrder(order,orderdetailList);
    }
}
