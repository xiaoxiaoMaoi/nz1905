package com.qf.cart.v1.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.qf.mapper")
public class CartV1ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartV1ServiceApplication.class, args);
    }

}
