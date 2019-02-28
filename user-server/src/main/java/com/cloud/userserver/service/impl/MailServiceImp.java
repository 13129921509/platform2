package com.cloud.userserver.service.impl;

import com.cloud.userserver.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author cai
 * @date 2019/2/25
 */
@Component
public class MailServiceImp implements MailService {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender sender;
    public void sendSimpleEmail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage =  new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText("您的验证码为:"+content);
        simpleMailMessage.setTo(to);
        try {
            sender.send(simpleMailMessage);
            System.out.println("发送成功!!");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发送失败");
        }
    }
}
