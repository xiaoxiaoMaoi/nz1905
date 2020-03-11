package com.qf.solr.v2.consumer;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class} )
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrixDashboard
public class SolrV2ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolrV2ConsumerApplication.class, args);
    }

}
