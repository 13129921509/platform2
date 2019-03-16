package com.cloud.publicmodel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("shop_detail")
public class ShopDetailEntity {
    @TableId("id")
    String id;

    @TableField("email")
    String email;

    @TableField("password")
    String password;

    @TableField("username")
    String username;

    @TableField("telephone")
    String telephone;

    @TableField("name")
    String name;

    @TableField("address")
    String address;

    @TableField("introduce")
    String introduce;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public ShopDetailEntity(String email, String password, String username, String telephone, String name, String address, String introduce) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.telephone = telephone;
        this.name = name;
        this.address = address;
        this.introduce = introduce;
    }
}
