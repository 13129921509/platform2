package com.cloud.userserver.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cloud.publicmodel.entity.RegistryUserEntity;
import com.cloud.publicmodel.client.RedisClient;
import com.cloud.userserver.lisent.RegistryLisent;
import com.cloud.userserver.mapper.UserHeaderMapper;
import com.cloud.userserver.service.MailService;
import com.cloud.userserver.service.impl.UserServiceImp;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserRegisterController {
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
    UserHeaderMapper userMapper;
    /*

    1.数据库中比对，是由存在冲突信息，不存在则接着走
    2.发送邮箱
    3.存入验证码，设置超时时间
     */
    @RequestMapping(value = "/registrycode",method = RequestMethod.POST)
    public boolean registrycode(@RequestBody HashMap map) throws InterruptedException {
        String email = (String) map.get("email");
        RegistryUserEntity entity = userServiceImp.getRegistryUser(email);
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
            UpdateWrapper<RegistryUserEntity> wrapper= new UpdateWrapper<RegistryUserEntity>();
//            userMapper.insert()
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping(value = "/user/registry",method = RequestMethod.PUT)
    public void registry(@RequestBody RegistryUserEntity map){
        userMapper.insert(map);
    }
}
