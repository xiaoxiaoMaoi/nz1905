package com.qf.shop.demo.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ShopDemoEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopDemoEurekaServerApplication.class, args);
    }

}
