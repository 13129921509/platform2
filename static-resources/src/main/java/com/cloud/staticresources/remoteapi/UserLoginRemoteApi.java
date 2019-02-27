package com.cloud.staticresources.remoteapi;

import com.alibaba.fastjson.JSONObject;
import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.response.Result;
import com.cloud.publicmodel.session.HttpClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@FeignClient("user-server")
@Component
public interface UserLoginRemoteApi {
    @RequestMapping(value = "/loginCode",method = RequestMethod.POST)
    boolean loginCode(@RequestBody HashMap map);

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    String login(@RequestBody LoginUserEntity user);

}
