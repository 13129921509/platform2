package com.cloud.orderserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.orderserver.mapper.OrderUserMapper;
import com.cloud.publicmodel.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderUserController {
    @Autowired
    private OrderUserMapper orderUserMapper;

    @RequestMapping("/userEmail/{userEmail}")
    public List<OrderEntity> getOrdersByUserId(@PathVariable("userEmail") String userEmail){
        LambdaQueryWrapper<OrderEntity> wrapper = new LambdaQueryWrapper<OrderEntity>();
        List<OrderEntity> list = orderUserMapper.getOrderListByUsers(wrapper.eq(OrderEntity::getUserEmail,userEmail));
        return list;
    }
}
