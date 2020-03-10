package com.qf.solr.v2.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.qf.mapper")
public class SolrV2ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolrV2ServiceApplication.class, args);
    }

}
