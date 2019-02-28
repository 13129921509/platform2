package com.cloud.shopserver.lisent;


import com.cloud.publicmodel.client.RedisClient;
import com.cloud.shopserver.service.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistryLisent {
    @Autowired
    MailService mailService;
    @Autowired
    RedisClient redisClient;
    //耗时操作:发送邮件
    @RabbitListener(queues = "registry-send-email")
    public void sendEmail(String email) throws InterruptedException {
        String content = String.valueOf(UUID.randomUUID()).split("-")[0];
        redisClient.setObjectOfString("registrycode:"+email,content,60000);
        System.out.println(content);
        mailService.sendSimpleEmail(email,"淘机网验证码",content);
    }


}
