package com.cloud.userserver.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cloud.publicmodel.entity.RegistryUserEntity;
import com.cloud.publicmodel.entity.UserDetailsEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public interface UserDetailMapper extends BaseMapper<UserDetailsEntity> {

    @Cacheable(cacheNames = {"UserDetailsEntity"},key = "methodName +#p0")
    @Select("select * from user_details ${ew.customSqlSegment}")
    UserDetailsEntity getUserDetailsEntity(@Param(Constants.WRAPPER) Wrapper<UserDetailsEntity> wrapper);
}
