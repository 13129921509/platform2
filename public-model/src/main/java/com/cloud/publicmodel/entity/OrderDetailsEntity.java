package com.cloud.publicmodel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("order_details")
public class OrderDetailsEntity implements Serializable {
    @TableField("commodityCode")
    String commodityCode;

    @TableField("commodityName")
    String commodityName;

    @TableField("number")
    String number;

    @TableField("price")
    String price;

    @TableField("orderCode")
    String orderCode;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public OrderDetailsEntity(String commodityCode, String commodityName, String number, String price, String orderCode) {
        this.commodityCode = commodityCode;
        this.commodityName = commodityName;
        this.number = number;
        this.price = price;
        this.orderCode = orderCode;
    }

    public OrderDetailsEntity(){}
}
