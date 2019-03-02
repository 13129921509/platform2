package com.cloud.commodityserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableEurekaClient
@ComponentScan(basePackages = {"com.cloud.publicmodel","com.cloud.commodityserver.*"})
@SpringBootApplication
@EnableTransactionManagement
public class CommodityServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommodityServerApplication.class, args);
    }

}
