package com.cloud.staticresources.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.response.AbstractResponseBody;
import com.cloud.publicmodel.entity.response.ErrorResponseBody;
import com.cloud.publicmodel.entity.response.Result;
import com.cloud.publicmodel.entity.response.SuccessResponseBody;
import com.cloud.staticresources.remoteapi.UserLoginRemoteApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class UserLoginController {
    @Autowired
    UserLoginRemoteApi userLoginRemoteApi;

    @RequestMapping(value = "/loginCode",method = RequestMethod.POST)
    public Result loginCode(@RequestBody HashMap map){
        if (userLoginRemoteApi.loginCode(map)){
            return new SuccessResponseBody("Successful Verification Code!!!");
        }else{
            return new ErrorResponseBody("该邮箱未注册!!!",ErrorResponseBody.ErrorCode.VERIFICATION_CODE_ERROR.getCode());
        }
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public Result login(@RequestBody LoginUserEntity entity,HttpServletRequest request){
        String str = userLoginRemoteApi.login(entity);
        AbstractResponseBody result = JSONObject.parseObject(str,AbstractResponseBody.class);
        if (result.getCode() == 200){
            //存放到本地session中，用来以后做业务操作前的验证
            request.getSession().setAttribute("entity",entity);
        }
        return result;
    }
}
