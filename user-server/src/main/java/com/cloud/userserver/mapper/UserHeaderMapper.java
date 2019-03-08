package com.cloud.userserver.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.publicmodel.entity.RegistryUserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface UserHeaderMapper extends BaseMapper<RegistryUserEntity>{
    @Select("select * from user_header ${ew.customSqlSegment}")
    RegistryUserEntity getRegistryUser(@Param(Constants.WRAPPER) Wrapper<RegistryUserEntity> wrapper);

    @Update("update from user_header ${ew.customSqlSegment}")
    void addRegistryUser(@Param(Constants.WRAPPER) Wrapper<RegistryUserEntity> wrapper);

    @Select("select count(*) from user_header ${ew.customSqlSegment}")
    int login(@Param(Constants.WRAPPER) Wrapper<RegistryUserEntity> wrapper);
}
