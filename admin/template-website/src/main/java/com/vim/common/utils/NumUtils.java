package com.vim.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @作者 Administrator
 * @时间 2019-08-11 9:40
 * @版本 1.0
 */
public class NumUtils {

    private NumUtils(){}

    /**
     * 格式化为两位小数
     */
    public static String format(BigDecimal num){
        return new DecimalFormat("0.00").format(num.doubleValue());
    }
}
