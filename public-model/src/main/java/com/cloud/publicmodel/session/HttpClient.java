package com.cloud.publicmodel.session;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class HttpClient implements Serializable {
    String checkCode;
    String account;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String data;

    public HttpClient(){}
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = dateFormat.format(new Date());
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public HttpClient(String checkCode,String account){
        this.checkCode = checkCode;
        this.account = account;
    }

    @Override
    public String toString() {
        return "HttpClient{" +
                "checkCode='" + checkCode + '\'' +
                ", account='" + account + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
