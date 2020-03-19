package com.qf.order.v1.consumer.service;

import com.qf.bean.Order;
import com.qf.bean.Orderdetail;
import org.aspectj.weaver.ast.Var;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Ray.Cheng
 * @Date 2020/3/14
 */
@FeignClient(name = "order-v1-service")
public interface IOrderService {

    @RequestMapping("/order/add")
    int createOrder(@RequestBody Order order, @RequestParam List<Orderdetail> orderdetailList);
}
