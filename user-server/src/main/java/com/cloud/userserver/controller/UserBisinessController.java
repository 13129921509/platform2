package com.cloud.userserver.controller;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cloud.publicmodel.client.RedisClient;
import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.RegistryUserEntity;
import com.cloud.publicmodel.entity.UserDetailsEntity;
import com.cloud.publicmodel.entity.response.AbstractResponseBody;
import com.cloud.publicmodel.entity.response.ErrorResponseBody;
import com.cloud.publicmodel.entity.response.Result;
import com.cloud.publicmodel.entity.response.SuccessResponseBody;
import com.cloud.publicmodel.session.HttpClient;
import com.cloud.userserver.mapper.UserDetailMapper;
import com.cloud.userserver.mapper.UserHeaderMapper;
import com.cloud.userserver.service.impl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;

@RestController
public class UserBisinessController {
    @Autowired
    UserDetailMapper userDetailMapper;
    @Autowired
    UserServiceImp serviceImp;
    @Autowired
    RedisClient redisClient;
    @Autowired
    UserHeaderMapper userHeaderMapper;
    @RequestMapping(value = "/httpclient",method = RequestMethod.POST)
    public HttpClient getHttpClient(@RequestBody LoginUserEntity entity){
        return serviceImp.getHttpClient("HttpClient:"+entity.getEmail());

    }

    @RequestMapping(value = "/checkHealth",method = RequestMethod.GET)
    public String checkHealth(){
        return "123";
    }


    /**
     * x\显示出用户的详细信息
     * @param entity
     * @return
     */
    @RequestMapping(value = "/user/detail",method = RequestMethod.POST)
    public UserDetailsEntity userDetailsEntity(@RequestBody LoginUserEntity entity){
        LambdaQueryWrapper<UserDetailsEntity> wrapper = new LambdaQueryWrapper<UserDetailsEntity>();
        UserDetailsEntity userDetailsEntity = userDetailMapper.getUserDetailsEntity(wrapper.eq(UserDetailsEntity::getEmail,entity.getEmail()));;

        redisClient.setObjectOfHash("userDetails:email:"+userDetailsEntity.getEmail(),userDetailsEntity);
//        System.out.println(redisClient.getObjectOfHash("userDetails:email:"+entity.getEmail()));
        return userDetailsEntity;
    }

    /**
     * 修改用户信息
     */
    @Transactional
    @RequestMapping(value = "/edit/{email}",method = RequestMethod.PUT)
    public AbstractResponseBody judgeUser(@PathVariable String email,@RequestBody Map map){
        Field [] fields = RegistryUserEntity.class.getFields();
        Field judgeFiled = null;
        for (Field f : fields){
            if (f.getAnnotationsByType(TableField.class) != null){
                if (map.get("cs").equals(f.getAnnotation(TableField.class).value())){
                    judgeFiled = f;
                }
            }
        }
        if (judgeFiled != null){
            if (judgeFiled.getAnnotation(TableField.class).value() == "password"){
                userDetailMapper.update(new UserDetailsEntity(),
                        new LambdaUpdateWrapper<UserDetailsEntity>().setSql("password = "+ map.get("value")).eq(UserDetailsEntity::getEmail,email));
            }else if (judgeFiled.getAnnotation(TableField.class).value() == "email"){
                userDetailMapper.update(new UserDetailsEntity(),
                        new LambdaUpdateWrapper<UserDetailsEntity>().setSql("email = "+ map.get("value")).eq(UserDetailsEntity::getEmail,email));
            }else if (judgeFiled.getAnnotation(TableField.class).value() == "telephone"){
                userDetailMapper.update(new UserDetailsEntity(),
                        new LambdaUpdateWrapper<UserDetailsEntity>().setSql("telephone = "+ map.get("value")).eq(UserDetailsEntity::getEmail,email));
            }
        }
        int result = userHeaderMapper.update(new RegistryUserEntity(),new LambdaUpdateWrapper<RegistryUserEntity>().setSql(map.get("cs")+" = "+map.get("value")).eq(RegistryUserEntity::getEmail,email));
        if(result == 1){
            return new AbstractResponseBody("success",200);
        }else{
            return new AbstractResponseBody("修改失敗",ErrorResponseBody.ErrorCode.INEXPLICABLE_ERROR.getCode());
        }
    }


}
