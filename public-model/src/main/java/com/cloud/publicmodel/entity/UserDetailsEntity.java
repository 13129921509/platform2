package com.cloud.publicmodel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("user_details")
public class UserDetailsEntity implements Serializable {
    @TableField("realName")
    String realName;

    @TableField("receivingTel")
    String receivingTel;

    @TableField("receivingAddress")
    String receivingAddress;

    @TableField("username")
    String username;

    @TableField("passwords")
    String passwords;

    @TableField("telephone")
    String telephone;

    @TableField("email")
    String email;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getReceivingTel() {
        return receivingTel;
    }

    public void setReceivingTel(String receivingTel) {
        this.receivingTel = receivingTel;
    }

    public String getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(String receivingAddress) {
        this.receivingAddress = receivingAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDetailsEntity(String realName, String receivingTel, String receivingAddress, String username, String passwords, String telephone, String email) {
        this.realName = realName;
        this.receivingTel = receivingTel;
        this.receivingAddress = receivingAddress;
        this.username = username;
        this.passwords = passwords;
        this.telephone = telephone;
        this.email = email;
    }

    public UserDetailsEntity(){}
}
