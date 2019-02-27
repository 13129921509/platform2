package com.cloud.publicmodel.entity.response;

import java.io.Serializable;

public class SuccessResponseBody implements Result,Serializable{
    /**
     * 内容
     */
    private String msg;

    private int code;

    public SuccessResponseBody(){

    }

    public SuccessResponseBody(String success)
    {
        this.msg = success;
        this.code = 200;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
