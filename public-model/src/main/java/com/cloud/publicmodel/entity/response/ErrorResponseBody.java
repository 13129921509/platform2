package com.cloud.publicmodel.entity.response;

public class ErrorResponseBody implements Result
{
    /**
     * 错误内容
     */
    private String error;

    /**
     * 自定义错误码
     */
    private int code;


    public ErrorResponseBody(String error, int code)
    {
        this.error = error;
        this.code = code;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }


    public enum ErrorCode{/**
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

