package com.cloud.userserver.controller;

import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.RegistryUserEntity;
import com.cloud.userserver.client.RedisClient;
import com.cloud.userserver.lisent.RegistryLisent;
import com.cloud.userserver.mapper.UserMapper;
import com.cloud.userserver.service.MailService;
import com.cloud.userserver.service.impl.UserServiceImp;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class UserLoginController {
    @Autowired
    MailService mailService;

    @Autowired
    RedisClient redisClient;

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    RegistryLisent registryLisent;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    UserMapper userMapper;

    /**
     * 获得验证码
     * @param map 获得验证码所需的邮箱
     * @return 一个布尔值判断成功或是失败
     * @throws InterruptedException
     */
    @RequestMapping(value = "/loginCode",method = RequestMethod.POST)
    public boolean loginCode(@RequestBody HashMap map) throws InterruptedException {
        String email = (String) map.get("email");
        RegistryUserEntity entity = userServiceImp.getRegistryUser(email);
        if (entity==null){
            return false;
        }
        //在此使用消息队列来进行异步调用
        amqpTemplate.convertAndSend("login-send-email",email);
        return true;
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String login(@RequestBody LoginUserEntity user, HttpServletRequest request) throws InterruptedException {
       return  userServiceImp.login(user,request);
    }
}
