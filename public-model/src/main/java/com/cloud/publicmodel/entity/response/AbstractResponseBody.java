package com.cloud.publicmodel.entity.response;

public class AbstractResponseBody implements Result{
    private String msg;

    private int code;

    public AbstractResponseBody(){

    }

    public AbstractResponseBody(String success,int code)
    {
        this.msg = success;
        this.code = code;
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
