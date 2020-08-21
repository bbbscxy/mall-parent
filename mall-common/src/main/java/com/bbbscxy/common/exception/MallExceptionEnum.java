package com.bbbscxy.common.exception;

/**
 * @作者 Administrator
 * @时间 2020-08-21 16:02
 * @版本 1.0
 * @说明
 */
public enum MallExceptionEnum {

    CODE_500(500, "内部服务器错误");

    private Integer code;
    private String msg;

    MallExceptionEnum(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
