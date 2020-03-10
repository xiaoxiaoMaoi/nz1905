package com.qf.back.v3.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.qf.mapper")
public class BackV3ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackV3ServiceApplication.class, args);
    }

}
