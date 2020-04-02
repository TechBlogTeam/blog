package com.blog.common.utils;

import com.blog.common.constants.Constant;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private String code;
    private String message;
    private T result;
    private boolean status = false;

    public Result() {
        super();
    }
    private Result(Boolean status, String message, T result, String code) {
        this.message = message;
        this.result = result;
        this.code = code;
        this.status = status;
    }
    private Result(Boolean status, String message, String code) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    /**
     * 系统错误500返回Result
     *
     * @param message 错误原因
     * @return Result<T>
     */
    public static <T> Result<T> fail(String message,String code) {
        return new Result<T>(false,message, code);
    }
    /***
     * 请求成功Result
     *
     * @param result 返回结果
     * @return Result<T>
     */
    public static <T> Result<T> success(T result) {
        return new Result<>(true, Constant.RESPONSE_MESSAGE_SUCCESS, result, Constant.RESPONSE_CODE_SUCCESS);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
