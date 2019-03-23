package com.cloud.publicmodel.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单中一对多的关系
 */
public class OrderModel implements Serializable {
    public OrderEntity orderEntity;
    public List<OrderDetailsEntity> orderDetailsEntityList;

    public OrderModel(OrderEntity orderEntity,List<OrderDetailsEntity> orderDetailsEntityList){
        this.orderEntity = orderEntity;
        this.orderDetailsEntityList = orderDetailsEntityList;
    }

    public OrderModel(OrderEntity orderEntity,OrderDetailsEntity orderDetailsEntity){
        this.orderEntity = orderEntity;
        orderDetailsEntityList = new ArrayList<OrderDetailsEntity>();
        orderDetailsEntityList.add(orderDetailsEntity);
    }

    public OrderModel(){}

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public List<OrderDetailsEntity> getOrderDetailsEntityList() {
        return orderDetailsEntityList;
    }

    public void setOrderDetailsEntityList(List<OrderDetailsEntity> orderDetailsEntityList) {
        this.orderDetailsEntityList = orderDetailsEntityList;
    }
}
