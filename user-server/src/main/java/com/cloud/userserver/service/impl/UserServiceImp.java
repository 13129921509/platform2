package com.cloud.userserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.RegistryUserEntity;
import com.cloud.publicmodel.entity.response.ErrorResponseBody;
import com.cloud.publicmodel.entity.response.Result;
import com.cloud.publicmodel.entity.response.SuccessResponseBody;
import com.cloud.publicmodel.session.HttpClient;
import com.cloud.publicmodel.client.RedisClient;
import com.cloud.userserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Component
@Transactional
public class UserServiceImp {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisClient redisClient;

    /**
     * 获取用户
     * @param value email
     * @return
     */
    public RegistryUserEntity getRegistryUser(String value){
        LambdaQueryWrapper<RegistryUserEntity> wrapper = new LambdaQueryWrapper<>();
        RegistryUserEntity entity = userMapper.getRegistryUser(wrapper.eq(RegistryUserEntity::getEmail,value));
        return entity;
    }

    /**
     * 登录业务
     * @param entity
     * @param request
     * @return
     * @throws InterruptedException
     */
    public String login(LoginUserEntity entity, HttpServletRequest request) throws InterruptedException {
        LambdaQueryWrapper<RegistryUserEntity> wrapper = new LambdaQueryWrapper<>();
        int count = userMapper.login(wrapper.eq(RegistryUserEntity::getEmail,entity.getEmail())
                .eq(RegistryUserEntity::getPassword,entity.getPassword()));
        if (count != 1){
            Result result = new ErrorResponseBody("账户与密码不匹配",ErrorResponseBody.ErrorCode.ACCOUNT_DOES_NOT_MATCH_PASSWORD.getCode());
            String jsonObject = JSONObject.toJSONString(result);
            return jsonObject;
        }
        String realyzm = (String) redisClient.getObjectOfString("logincode:"+entity.getEmail());
        if (entity.getYzm().equals(realyzm)){
            //request.getSession().setAttribute("key",new HttpClient(entity.getYzm(),entity.getEmail()));
            redisClient.setObjectOfObject("HttpClient:"+entity.getEmail(),new HttpClient(entity.getYzm(),entity.getEmail()));
            HttpClient client = (HttpClient) redisClient.getObjectOfObject("HttpClient:"+entity.getEmail());
            Result result = new SuccessResponseBody("success!!!");
            String jsonObject = JSONObject.toJSONString(result);
            return jsonObject;
        }else{
            Result result = new ErrorResponseBody("验证码错误",ErrorResponseBody.ErrorCode.VERIFICATION_CODE_ERROR.getCode());
            String jsonObject = JSONObject.toJSONString(result);
            return jsonObject;
        }
    }

    /**
     * 远程获得redis中的用户客户端信息
     * 服务与服务之间的redis交换，通常是为了实现session一致性
     * @param key: 通常是HttpClient:<Email></>
     */
    public HttpClient getHttpClient(String key){
        return (HttpClient) redisClient.getObjectOfObject(key);
    }
}
