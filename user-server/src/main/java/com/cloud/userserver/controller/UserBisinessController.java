package com.cloud.userserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.UserDetailsEntity;
import com.cloud.publicmodel.session.HttpClient;
import com.cloud.userserver.mapper.UserDetailMapper;
import com.cloud.userserver.mapper.UserHeaderMapper;
import com.cloud.userserver.service.impl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserBisinessController {
    @Autowired
    UserDetailMapper userDetailMapper;
    @Autowired
    UserServiceImp serviceImp;
    @RequestMapping(value = "/httpclient",method = RequestMethod.POST)
    public HttpClient getHttpClient(@RequestBody LoginUserEntity entity){
        return serviceImp.getHttpClient("HttpClient:"+entity.getEmail());

    }

    @RequestMapping(value = "/checkHealth",method = RequestMethod.GET)
    public String checkHealth(){
        return "123";
    }


    @RequestMapping(value = "/user/detail",method = RequestMethod.POST)
    public UserDetailsEntity userDetailsEntity(@RequestBody LoginUserEntity entity){
        LambdaQueryWrapper<UserDetailsEntity> wrapper = new LambdaQueryWrapper<UserDetailsEntity>();
        return userDetailMapper.getUserDetailsEntity(wrapper.eq(UserDetailsEntity::getEmail,entity.getEmail()));
    }
}
