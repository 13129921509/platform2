package com.cloud.staticresources.remoteapi;

import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.UserDetailsEntity;
import com.cloud.publicmodel.session.HttpClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@FeignClient("user-server")
@Component
public interface BusinessRemoteApi {
    @RequestMapping(value = "/httpclient",method = RequestMethod.POST)
    HttpClient getHttpClient(@RequestBody LoginUserEntity entity);

    @RequestMapping(value = "/user/detail",method = RequestMethod.POST)
    UserDetailsEntity userDetailsEntity(@RequestBody LoginUserEntity entity);


}
