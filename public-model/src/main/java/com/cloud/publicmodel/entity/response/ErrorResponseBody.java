package com.cloud.publicmodel.entity.response;

import java.io.Serializable;

public class ErrorResponseBody implements Result,Serializable
{
    /**
     * 错误内容
     */
    private String msg;

    /**
     * 自定义错误码
     */
    private int code;

    public ErrorResponseBody(){

    }

    public ErrorResponseBody(String msg, int code)
    {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String error)
    {
        this.msg = error;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }


    public enum ErrorCode{
        /**
         * 用户详细信息未定义
         */
        USER_DETAILS_UNDEFINED(40410),

        /**
         * 账户与密码不匹配
         */
        ACCOUNT_DOES_NOT_MATCH_PASSWORD(40405),
        /**
         * 验证码错误
         */
        VERIFICATION_CODE_ERROR(40404),
        /**
         * 邮件已使用
         */
        Email_IS_EXITS(40402),
        /**
         * 用户不存在
         */
        USER_NOT_FOUND(40401),

        /**
         * 用户已存在
         */
        USER_ALREADY_EXIST(40001),
        ;

        private int code;

        public int getCode()
        {
            return code;
        }

        ErrorCode(int code)
        {
            this.code = code;
        }
    }
}

