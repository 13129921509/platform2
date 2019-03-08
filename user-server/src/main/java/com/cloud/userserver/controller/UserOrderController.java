package com.cloud.userserver.controller;


import com.cloud.publicmodel.entity.LoginUserEntity;
import com.cloud.publicmodel.entity.OrderEntity;
import com.cloud.userserver.remoteApi.OrderRemoteApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class UserOrderController {

//    @Autowired
//    OrderRemoteApi orderRemoteApi;
//
//    @RequestMapping(value = "/")
//    public List<OrderEntity> getOrder(@RequestBody LoginUserEntity entity){
//        return orderRemoteApi.getOrdersList(entity.getEmail());
//    }
}
