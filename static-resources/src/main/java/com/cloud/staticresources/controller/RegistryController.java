package com.cloud.staticresources.controller;

import com.cloud.publicmodel.entity.RegistryUserEntity;
import com.cloud.publicmodel.entity.response.ErrorResponseBody;
import com.cloud.publicmodel.entity.response.Result;
import com.cloud.publicmodel.entity.response.SuccessResponseBody;
import com.cloud.staticresources.remoteapi.UserRegistryRemoteApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RegistryController {
    @Autowired
    UserRegistryRemoteApi userRegistryRemoteApi;
    @RequestMapping("/")
    public String index(Model model) {
        return "/index.html";
    }

    /**
     * 设置注册码
     * @param map
     */
    @RequestMapping(value = "/registrycode",method = RequestMethod.POST)
    public Result registrycode(@RequestBody HashMap map){
        boolean status = userRegistryRemoteApi.registrycode(map);
        if (!status){
            return new ErrorResponseBody("email is exits!",
                    ErrorResponseBody.ErrorCode.Email_IS_EXITS.getCode());
        }
        return new SuccessResponseBody("60秒后注册码失效");
    }
    /**
     * 注册用户
     * @param map
     */
    @RequestMapping(value = "/user/registry",method = RequestMethod.POST)
    public Result registry(@RequestBody RegistryUserEntity map){
//        不需要在这里判断邮件是否存在
//        而是在获取验证码的时候判断
//        boolean status = userRegistryRemoteApi.comparisonregistrycode(map);
//        if (status == false){
//            return new ErrorResponseBody("email is exits!",
//                    ErrorResponseBody.ErrorCode.Email_IS_EXITS.getCode());
//        }
        userRegistryRemoteApi.registry(map);
        return new SuccessResponseBody("success!!!");
    }
    /**
     * 判断验证码
     * @param map
     */
    @RequestMapping(value = "/checkYzm",method = RequestMethod.POST)
    public Result checkYzm(@RequestBody Map map){
        Boolean status = userRegistryRemoteApi.comparisonregistrycode(map);
        if (status == true){
            return new SuccessResponseBody("success!!!");
        }else{
            return new ErrorResponseBody("Verification code error!!!",
                    ErrorResponseBody.ErrorCode.VERIFICATION_CODE_ERROR.getCode());
        }
    }



}
