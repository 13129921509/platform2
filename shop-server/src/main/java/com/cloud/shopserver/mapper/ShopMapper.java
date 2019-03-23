package com.cloud.shopserver.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cloud.publicmodel.entity.RegistryShopEntity;
import com.cloud.publicmodel.entity.RegistryUserEntity;
import com.cloud.publicmodel.entity.ShopDetailEntity;
import com.cloud.publicmodel.entity.ShopHeaderEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface ShopMapper extends BaseMapper<RegistryShopEntity> {
    @Select("select * from shop_header ${ew.customSqlSegment}")
    RegistryShopEntity getRegistryUser(@Param(Constants.WRAPPER) LambdaQueryWrapper<RegistryShopEntity> wrapper);

    @Update("update from shop_header ${ew.customSqlSegment}")
    void addRegistryUser(@Param(Constants.WRAPPER) Wrapper<RegistryShopEntity> wrapper);

    @Select("select count(*) from shop_header ${ew.customSqlSegment}")
    int login(@Param(Constants.WRAPPER) Wrapper<RegistryShopEntity> wrapper);


}
