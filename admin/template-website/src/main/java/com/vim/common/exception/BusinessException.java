package com.vim.common.exception;

/**
 * @作者 Administrator
 * @时间 2019-08-19 16:06
 * @版本 1.0
 * @说明 业务异常
 */
public class BusinessException extends Exception{

    public BusinessException(String message) {
        super(message);
    }

}
