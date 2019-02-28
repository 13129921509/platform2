package com.cloud.shopserver;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableEurekaClient
@ComponentScan(basePackages = {"com.cloud.publicmodel","com.cloud.shopserver.*"})
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.cloud.shopserver.mapper")
public class ShopServerApplication {
    @Bean
    @Profile({"default"})
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }

    public static void main(String[] args) {
        SpringApplication.run(ShopServerApplication.class, args);
    }

}
