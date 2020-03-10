package com.qf.cart.v2.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.qf.mapper")
public class CartV2ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartV2ServiceApplication.class, args);
    }

}
