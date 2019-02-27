package com.cloud.staticresources.remoteapi;

import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.session.HttpClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("user-server")
@Component
public interface BusinessRemoteApi {
    @RequestMapping(value = "/httpclient",method = RequestMethod.POST)
    HttpClient getHttpClient(@RequestBody LoginUserEntity entity);
}
