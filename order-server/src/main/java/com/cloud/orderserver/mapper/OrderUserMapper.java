package com.cloud.orderserver.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cloud.publicmodel.entity.OrderEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderUserMapper extends BaseMapper<OrderEntity> {

    @Select("SELECT * FROM order_ ${ew.customSqlSegment} ")
    List<OrderEntity> getOrderListByUsers(@Param(Constants.WRAPPER) Wrapper<OrderEntity> wrapper);



}
