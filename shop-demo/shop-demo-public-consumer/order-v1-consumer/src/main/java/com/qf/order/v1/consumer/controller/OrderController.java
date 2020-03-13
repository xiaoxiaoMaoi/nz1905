package com.qf.order.v1.consumer.controller;

import com.qf.entity.TOrder;
import com.qf.entity.TOrderdetail;
import com.qf.order.v1.consumer.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Ray.Cheng
 * @Date 2020/3/14
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /*
    1.用户点击结算按钮，
    2.判断用户是否登录，若未登录，跳转至登录页面
    3.若登录，跳到pay.html
    4.接收到order,orderDetail数据
    5.点击提交订单，跳转至支付页面
    6.支付成功后，去数据库order表和orderDetail表中添加数据记录
    7.跳转至success页面
     */
    @PostMapping("/add")
    public String addOrder(TOrder order, TOrderdetail orderdetail){

    }


}
