package com.qf.shop.demo.shopping.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.qf.mapper")
public class ShopDemoShoppingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopDemoShoppingServiceApplication.class, args);
    }

}
