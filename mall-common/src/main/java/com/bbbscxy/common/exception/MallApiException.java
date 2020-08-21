package com.bbbscxy.common.exception;

/**
 * @作者 Administrator
 * @时间 2020-08-21 15:54
 * @版本 1.0
 * @说明
 */
public class MallApiException extends Exception{

    private static final long serialVersionUID = 1L;

    private Integer code; //错误码

    public MallApiException() {}

    public MallApiException(MallExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
