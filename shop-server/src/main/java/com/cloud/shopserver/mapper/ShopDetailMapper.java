package com.cloud.shopserver.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cloud.publicmodel.entity.ShopDetailEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public interface ShopDetailMapper extends BaseMapper<ShopDetailEntity> {

    @Cacheable(cacheNames = {"ShopDetailsEntity"},key = "methodName +#p0")
    @Select("select * from shop_detail ${ew.customSqlSegment}")
    ShopDetailEntity getShopDetailsEntity(@Param(Constants.WRAPPER) Wrapper<ShopDetailEntity> wrapper);
}
