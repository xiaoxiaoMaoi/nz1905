package com.qf.regist.consumer.config;/*
    @auter wwx
    @date 2020/3/17
*/

import com.qf.constant.RabbitConstant;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbigmqConfig {

    @Bean
    public TopicExchange getExchange(){
        return new TopicExchange(RabbitConstant.EMAIL_TOPIC_EXCHANGE);
    }




}