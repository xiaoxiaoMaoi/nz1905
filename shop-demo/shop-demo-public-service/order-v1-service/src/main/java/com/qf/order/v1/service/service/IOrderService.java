package com.qf.order.v1.service.service;

import com.qf.bean.Order;
import com.qf.bean.Orderdetail;

import java.util.List; /**
 * @Author Ray.Cheng
 * @Date 2020/3/19
 */
public interface IOrderService {
    int createOrder(Order order, List<Orderdetail> orderdetailList);
}
