package com.cloud.publicmodel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("order_details")
public class OrderDetailsEntity {
    @TableField("commodityCode")
    String commodityCode;

    @TableField("commodityName")
    String commodityName;

    @TableField("number")
    Integer number;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public OrderDetailsEntity(String commodityCode, String commodityName, Integer number, String price, String orderCode) {
        this.commodityCode = commodityCode;
        this.commodityName = commodityName;
        this.number = number;
        this.price = price;
        this.orderCode = orderCode;
    }
}
