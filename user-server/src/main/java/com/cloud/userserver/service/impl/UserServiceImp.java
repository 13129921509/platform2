package com.cloud.userserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.publicmodel.entity.RegistryUserEntity;
import com.cloud.userserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserServiceImp {
    @Autowired
    UserMapper userMapper;
    public RegistryUserEntity getRegistryUser(String value){
        LambdaQueryWrapper<RegistryUserEntity> wrapper = new LambdaQueryWrapper<>();
        RegistryUserEntity entity = userMapper.getRegistryUser(wrapper.eq(RegistryUserEntity::getEmail,value));
        return entity;
    }
}
