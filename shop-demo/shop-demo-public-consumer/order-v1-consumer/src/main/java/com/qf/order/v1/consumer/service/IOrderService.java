package com.qf.order.v1.consumer.service;

import org.aspectj.weaver.ast.Var;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author Ray.Cheng
 * @Date 2020/3/14
 */
@FeignClient(name = "order-v1-service")
public interface IOrderService {
}
