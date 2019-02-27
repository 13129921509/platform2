package com.cloud.staticresources.controller;

import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.session.HttpClient;
import com.cloud.staticresources.remoteapi.BusinessRemoteApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BusinessController {
    @Autowired
    BusinessRemoteApi businessRemoteApi;
    @PostMapping("/key")
    public HttpClient getClient(HttpServletRequest request){
        LoginUserEntity entity = (LoginUserEntity) request.getSession().getAttribute("entity");
        return businessRemoteApi.getHttpClient(entity);
    }
}
