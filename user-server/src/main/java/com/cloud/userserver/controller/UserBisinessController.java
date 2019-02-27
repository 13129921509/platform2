package com.cloud.userserver.controller;

import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.session.HttpClient;
import com.cloud.userserver.service.impl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserBisinessController {
    @Autowired
    UserServiceImp serviceImp;
    @RequestMapping(value = "/httpclient",method = RequestMethod.POST)
    public HttpClient getHttpClient(@RequestBody LoginUserEntity entity){
        return serviceImp.getHttpClient("HttpClient:"+entity.getEmail());

    }
}
