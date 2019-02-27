package com.cloud.userserver.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public Queue queue(){ return new Queue("registry-send-email"); }
    @Bean
    public Queue queue2(){ return new Queue("login-send-email"); }
}
