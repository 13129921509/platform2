package com.cloud.userserver;

import com.cloud.userserver.service.MailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MailTest {
    @Autowired
    MailService service;
    @Test
    public void sendMail(){
        service.sendSimpleEmail("942445346@qq.com","淘机王注册码","注册码:123");
    }
}
