package com.qf.shop.demo.regist.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ShopDemoRegistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopDemoRegistServiceApplication.class, args);
    }

}
