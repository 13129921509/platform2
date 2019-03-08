package com.cloud.userserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.publicmodel.client.RedisClient;
import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.UserDetailsEntity;
import com.cloud.publicmodel.session.HttpClient;
import com.cloud.userserver.mapper.UserDetailMapper;
import com.cloud.userserver.mapper.UserHeaderMapper;
import com.cloud.userserver.service.impl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserBisinessController {
    @Autowired
    UserDetailMapper userDetailMapper;
    @Autowired
    UserServiceImp serviceImp;
    @Autowired
    RedisClient redisClient;

    @RequestMapping(value = "/httpclient",method = RequestMethod.POST)
    public HttpClient getHttpClient(@RequestBody LoginUserEntity entity){
        return serviceImp.getHttpClient("HttpClient:"+entity.getEmail());

    }

    @RequestMapping(value = "/checkHealth",method = RequestMethod.GET)
    public String checkHealth(){
        return "123";
    }


    /**
     * x\显示出用户的详细信息
     * @param entity
     * @return
     */
    @RequestMapping(value = "/user/detail",method = RequestMethod.POST)
    public UserDetailsEntity userDetailsEntity(@RequestBody LoginUserEntity entity){
        LambdaQueryWrapper<UserDetailsEntity> wrapper = new LambdaQueryWrapper<UserDetailsEntity>();
        UserDetailsEntity userDetailsEntity = userDetailMapper.getUserDetailsEntity(wrapper.eq(UserDetailsEntity::getEmail,entity.getEmail()));;

        redisClient.setObjectOfHash("userDetails:email:"+userDetailsEntity.getEmail(),userDetailsEntity);
//        System.out.println(redisClient.getObjectOfHash("userDetails:email:"+entity.getEmail()));
        return userDetailsEntity;
    }
}
