package com.cloud.shopserver.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloud.publicmodel.client.RedisClient;
import com.cloud.publicmodel.entity.RegistryShopEntity;
import com.cloud.publicmodel.entity.RegistryUserEntity;
import com.cloud.publicmodel.impl.MailService;
import com.cloud.shopserver.lisent.RegistryLisent;
import com.cloud.shopserver.mapper.ShopMapper;
import com.cloud.shopserver.service.ShopServiceImp;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ShopRegisterController {
    @Autowired
    MailService mailService;

    @Autowired
    RedisClient redisClient;

    @Autowired
    ShopServiceImp shopServiceImp;

    @Autowired
    RegistryLisent registryLisent;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    ShopMapper shopMapper;
    /*

    1.数据库中比对，是由存在冲突信息，不存在则接着走
    2.发送邮箱
    3.存入验证码，设置超时时间
     */
    @RequestMapping(value = "/registrycode",method = RequestMethod.POST)
    public boolean registrycode(@RequestBody HashMap map) throws InterruptedException {
        String email = (String) map.get("email");
        RegistryShopEntity entity = shopServiceImp.getRegistryUser(email);
        if (entity!=null){
            return false;
        }

        //在此使用消息队列来进行异步调用
        amqpTemplate.convertAndSend("registry-send-email",email);
        return true;
    }
    //comparison
    /*
    1.从redis中获得验证码，比对，成功则记录数据库后反馈，否则反馈失败
     */
    @RequestMapping(value = "/comparisonregistrycode",method = RequestMethod.POST)
    public boolean comparisonregistrycode(@RequestBody Map map) throws InterruptedException {
        String yzm = (String) redisClient.getObjectOfString("registrycode:"+map.get("email"));
        if (map.get("yzm").equals(yzm)){
            UpdateWrapper<RegistryShopEntity> wrapper= new UpdateWrapper<RegistryShopEntity>();
//            userMapper.insert()
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping(value = "/shop/registry",method = RequestMethod.PUT)
    public void registry(@RequestBody RegistryShopEntity map){
        shopMapper.insert(map);
    }
}
