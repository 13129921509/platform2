package com.cloud.publicmodel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("order_")
public class OrderEntity {
    @TableField("orderCode")
    String orderCode;

    @TableField("shopCode")
    String shopCode;

    @TableField("userEmail")
    String userEmail;

    @TableField("number")
    String number;

    @TableField("address")
    String address;

    @TableField("consignee")
    String consignee;

    @TableField("status")
    String status;

    @TableField("startingTime")
    String startingTime;

    @TableField("price")
    String price;

    @TableField("commodityCode")
    String commodityCode;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public OrderEntity(){}

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public OrderEntity(String orderCode, String shopCode, String userEmail, String number, String address, String consignee, String status, String startingTime, String price, String commodityCode) {
        this.orderCode = orderCode;
        this.shopCode = shopCode;
        this.userEmail = userEmail;
        this.number = number;
        this.address = address;
        this.consignee = consignee;
        this.status = status;
        this.startingTime = startingTime;
        this.price = price;
        this.commodityCode = commodityCode;
    }
}
