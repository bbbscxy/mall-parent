package com.vim.common.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @作者 Administrator
 * @时间 2019-07-19 14:39
 * @版本 1.0
 * @说明 异常处理器
 */
@ControllerAdvice
public class BusinessExceptionHandler {

    /**
     * 无访问权限
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public ModelAndView unauthorizedException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return new ModelAndView("error/unauthorized");
    }

    /***
     * 404 处理
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView notFountHandler(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        return new ModelAndView("error/404");
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, Object>> bindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage += fieldError.getDefaultMessage() + ", ";
        }

        Map<String, Object> result = new HashMap<>();
        result.put("code", "FAIL");
        result.put("msg", errorMessage);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> businessException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "FAIL");
        result.put("msg", ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}