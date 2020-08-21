package com.bbbscxy.common.exceptionHandler;

import com.bbbscxy.common.exception.MallApiException;
import com.bbbscxy.common.response.MallResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @作者 Administrator
 * @时间 2020-08-21 15:58
 * @版本 1.0
 * @说明
 */
@ControllerAdvice
public class MallGlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = MallApiException.class)
    public MallResponseEntity<Map> mallApiException(MallApiException ex){
        return new MallResponseEntity<Map>().fail(ex.getCode(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public MallResponseEntity<Map> exception(Exception ex){
        return new MallResponseEntity<Map>().fail(ex.getMessage());
    }
}
