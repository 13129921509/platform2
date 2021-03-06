package com.cloud.publicmodel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

public class LoginUserEntity {
    String email;
    String password;
    String yzm;

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

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }


    public LoginUserEntity(String email, String password, String yzm) {
        this.email = email;
        this.password = password;
        this.yzm = yzm;
    }

    public LoginUserEntity(){}
}
