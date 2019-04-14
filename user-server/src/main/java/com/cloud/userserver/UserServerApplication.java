package com.cloud.userserver;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.cloud.publicmodel.dao.UserDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.cloud.userserver.mapper")
@ComponentScan(basePackages = {"com.cloud.publicmodel.*","com.cloud.userserver.*"})
public class UserServerApplication {
    @Bean
    @Profile({"default"})
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }

    public static void main(String[] args) {
        new UserDao().login();
        SpringApplication.run(UserServerApplication.class, args);
    }

}
