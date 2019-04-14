package com.cloud.orderserver;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableEurekaClient
@ComponentScan(basePackages = {"com.cloud.publicmodel","com.cloud.orderserver.*"})
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.cloud.orderserver.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class OrderServerApplication {
    @Bean
    @Profile({"default"})
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }

}
