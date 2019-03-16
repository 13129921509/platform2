package com.cloud.publicmodel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("shop_header")
public class ShopHeaderEntity {
    @TableId("id")
    String id;

    @TableField("username")
    String username;

    @TableField("passwords")
    String passwords;

    @TableField("telephone")
    String telephone;

    @TableField("email")
    String email;

    @TableField("relationId")
    String relationId;

    @TableField("shopCode")
    String shopCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public ShopHeaderEntity(String username, String passwords, String telephone, String email, String relationId, String shopCode) {
        this.username = username;
        this.passwords = passwords;
        this.telephone = telephone;
        this.email = email;
        this.relationId = relationId;
        this.shopCode = shopCode;
    }
}
