package com.cloud.orderserver.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cloud.publicmodel.entity.OrderDetailsEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderDetailsUserMapper extends BaseMapper<OrderDetailsEntity> {

    @Cacheable(cacheNames = {"OrderListByUsers"},key = "#p1")
    @Select("SELECT commodityCode,commodityName,number,price,orderCode FROM order_details ${ew.customSqlSegment} ")
    public List<OrderDetailsEntity> getOrderDetailsEntities(@Param(Constants.WRAPPER)Wrapper<OrderDetailsEntity> wrapper,String orderCode);
}
