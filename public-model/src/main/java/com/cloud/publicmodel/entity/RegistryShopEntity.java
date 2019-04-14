package com.cloud.publicmodel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@TableName("shop_header")
public class RegistryShopEntity  implements Serializable {
    //    @TableId(value = "id")
//    Long id;

    @TableField(value = "username")
    String username;

    @TableField(value = "passwords")
    String password;

    @TableField(value = "telephone")
    String telephone;

    @TableField(value = "email")
    String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public RegistryShopEntity(){

    }

}
