package com.cloud.orderserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cloud.orderserver.mapper.OrderDetailsUserMapper;
import com.cloud.orderserver.mapper.OrderUserMapper;
import com.cloud.publicmodel.entity.OrderDetailsEntity;
import com.cloud.publicmodel.entity.OrderEntity;
import com.cloud.publicmodel.entity.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderUserController {
    @Autowired
    private OrderUserMapper orderUserMapper;

    @Autowired
    private OrderDetailsUserMapper orderDetailsUserMapper;
    @RequestMapping("/userEmail/{userEmail}")
    public List<OrderEntity> getOrdersByUserId(@PathVariable("userEmail") String userEmail){
        LambdaQueryWrapper<OrderEntity> wrapper = new LambdaQueryWrapper<OrderEntity>();
        List<OrderEntity> list = orderUserMapper.getOrderListByUsers(wrapper.eq(OrderEntity::getUserEmail,userEmail));
        return list;
    }

    @RequestMapping("/orderCode/{orderCode}")
    public List<OrderDetailsEntity> getOrderDetailsEntities(@PathVariable("orderCode") String orderCode){
        LambdaQueryWrapper<OrderDetailsEntity> wrapper = new LambdaQueryWrapper<OrderDetailsEntity>();
        List<OrderDetailsEntity> list = orderDetailsUserMapper.getOrderDetailsEntities(wrapper.eq(OrderDetailsEntity::getOrderCode,orderCode),orderCode);
        return list;

    }

    /**
     * 新增订单
     * @return
     */
    @Transactional
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addOrderHeader(@RequestBody OrderModel orderModel){
        Integer num = 0;
        try{
            orderUserMapper.insert(orderModel.orderEntity);
            for (OrderDetailsEntity entity : orderModel.orderDetailsEntityList){
                orderDetailsUserMapper.insert(entity);
            }
            return String.valueOf(num = orderModel.orderDetailsEntityList.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "0";
    }
    @RequestMapping(value = "/order",method = RequestMethod.GET)
    public List<OrderEntity> getOrderList(@RequestBody Map<String,String> map){
        LambdaQueryWrapper<OrderEntity> wrapper = new LambdaQueryWrapper<OrderEntity>();
       return  orderUserMapper.getOrderListByUsers(wrapper.eq(OrderEntity::getUserEmail,map.get("email")));
    }
}
