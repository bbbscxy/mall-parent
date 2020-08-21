package com.bbbscxy.common.response;

import lombok.Data;

/**
 * @作者 Administrator
 * @时间 2020-08-21 14:59
 * @版本 1.0
 * @说明
 */
@Data
public class MallResponseEntity<T> {

    /**
     * 操作成功
     */
    private static final int SUCCESS = 200;

    /**
     * 操作失败
     */
    private static final int FAIL = 500;

    private int code;
    private String msg;
    private T data;

    /**
     * 成功返回
     */
    public MallResponseEntity success(T data) {
        this.code = SUCCESS;
        this.msg = "操作成功";
        this.data = data;
        return this;
    }

    /**
     * 成功返回
     */
    public MallResponseEntity success(String msg, T data) {
        this.code = SUCCESS;
        this.msg = msg;
        this.data = data;
        return this;
    }

    /**
     * 成功返回
     */
    public MallResponseEntity success(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        return this;
    }

    /**
     * 失败返回
     */
    public MallResponseEntity fail(String msg) {
        this.code = FAIL;
        this.msg = msg;
        return this;
    }

    /**
     * 失败返回
     */
    public MallResponseEntity fail(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }
}
