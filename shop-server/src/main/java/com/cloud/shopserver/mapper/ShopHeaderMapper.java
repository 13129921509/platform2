package com.cloud.shopserver.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cloud.publicmodel.entity.ShopHeaderEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ShopHeaderMapper extends BaseMapper<ShopHeaderEntity> {

    @Select("select * from shop_header ${ew.customSqlSegment}")
    ShopHeaderEntity getShopHeaderEntity(@Param(Constants.WRAPPER) Wrapper<ShopHeaderEntity> wrapper);

}
