package com.cloud.shopserver.lisent;

import com.cloud.publicmodel.client.RedisClient;
import com.cloud.publicmodel.impl.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LoginLisent {
    @Autowired
    MailService mailService;
    @Autowired
    RedisClient redisClient;

    @RabbitListener(queues = "login-send-email")
    public void sendEmail(String email) throws InterruptedException {
        String content = String.valueOf(UUID.randomUUID()).split("-")[0];
        redisClient.setObjectOfString("logincode:"+email,content,60000);
        System.out.println(redisClient.getObjectOfString("logincode:"+email));
        mailService.sendSimpleEmail(email,"淘机网验证码",content);
    }
}
