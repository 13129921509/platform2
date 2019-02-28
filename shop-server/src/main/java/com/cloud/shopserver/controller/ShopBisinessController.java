package com.cloud.shopserver.controller;

import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.session.HttpClient;
import com.cloud.shopserver.service.ShopServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopBisinessController {
    @Autowired
    ShopServiceImp serviceImp;
    @RequestMapping(value = "/httpclient",method = RequestMethod.POST)
    public HttpClient getHttpClient(@RequestBody LoginUserEntity entity){
        return serviceImp.getHttpClient("HttpClient:"+entity.getEmail());

    }

    @RequestMapping(value = "/checkHealth",method = RequestMethod.POST)
    public String checkHealth(){
        return "123";
    }
}
