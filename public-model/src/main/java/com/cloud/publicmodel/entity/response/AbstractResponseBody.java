package com.cloud.publicmodel.entity.response;

public abstract class AbstractResponseBody implements Result{
    private String msg;

    private int code;

    private Object obj;
    public AbstractResponseBody(){

    }

    public AbstractResponseBody(String success,int code,Object obj)
    {
        this.msg = success;
        this.code = code;
        this.obj = obj;
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

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
