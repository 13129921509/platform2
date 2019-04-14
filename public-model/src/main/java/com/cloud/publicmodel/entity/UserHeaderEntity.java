package com.cloud.publicmodel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("user_header")
public class UserHeaderEntity  implements Serializable {
    @TableField("username")
    String username;
    @TableField("passwords")
    String passwords;
    @TableField("telephone")
    String telephone;
    @TableField("email")
    String email;

    public UserHeaderEntity(String username, String passwords, String telephone, String email) {
        this.username = username;
        this.passwords = passwords;
        this.telephone = telephone;
        this.email = email;
    }

    public UserHeaderEntity(){}
}
