package com.vim.common.utils;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Set;

/**
 * @作者 Administrator
 * @时间 2019-07-21 15:35
 * @版本 1.0
 * @说明 校验工具类
 */
public class BeanValidatorUtils {

    private BeanValidatorUtils(){}

    /**
     * 校验数据
     * @param object    校验对象
     * @param result    校验结果
     * @param groups    校验组
     */
    public static <T> boolean validate(T object, Map<String, Object> result, Class<?>... groups){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, groups);
        if (CollectionUtils.isEmpty(constraintViolations)){
            return true;
        }else{
            for (ConstraintViolation<T> cv : constraintViolations) {
                result.put("code", "FAIL");
                result.put("msg", cv.getMessage());
            }
            return false;
        }
    }
}
