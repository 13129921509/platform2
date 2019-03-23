package com.cloud.staticresources.remoteapi;

import com.alibaba.fastjson.JSONObject;
import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.UserDetailsEntity;
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
    String  login(@RequestBody LoginUserEntity user);

    /**
     * x\显示出用户的详细信息
     * @param entity
     * @return
     */
    @RequestMapping(value = "/user/detail",method = RequestMethod.POST)
    UserDetailsEntity userDetailsEntity(@RequestBody LoginUserEntity entity);
}
