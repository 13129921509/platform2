package com.cloud.shopserver.controller;

import com.cloud.publicmodel.client.RedisClient;
import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.RegistryShopEntity;
import com.cloud.shopserver.lisent.RegistryLisent;
import com.cloud.shopserver.mapper.ShopMapper;
import com.cloud.shopserver.service.MailService;
import com.cloud.shopserver.service.impl.ShopServiceImp;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class ShopLoginController {
    @Autowired
    MailService mailService;

    @Autowired
    RedisClient redisClient;

    @Autowired
    ShopServiceImp userServiceImp;

    @Autowired
    RegistryLisent registryLisent;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    ShopMapper userMapper;

    /**
     * 获得验证码
     * @param map 获得验证码所需的邮箱
     * @return 一个布尔值判断成功或是失败
     * @throws InterruptedException
     */
    @RequestMapping(value = "/loginCode",method = RequestMethod.POST)
    public boolean loginCode(@RequestBody HashMap map) throws InterruptedException {
        String email = (String) map.get("email");
        RegistryShopEntity entity = userServiceImp.getRegistryUser(email);
        if (entity==null){
            return false;
        }
        //在此使用消息队列来进行异步调用
        amqpTemplate.convertAndSend("login-send-email",email);
        return true;
    }

    @RequestMapping(value = "/shop/login",method = RequestMethod.POST)
    public String login(@RequestBody LoginUserEntity user, HttpServletRequest request) throws InterruptedException {
       return  userServiceImp.login(user,request);
    }
}
