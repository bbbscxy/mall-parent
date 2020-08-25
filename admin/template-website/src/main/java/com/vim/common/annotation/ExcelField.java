package com.vim.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @作者 Administrator
 * @时间 2019-07-22 9:29
 * @版本 1.0
 * @说明 Excel 导入导出注解字段
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

    /**
     * 类型
     */
    enum Type{
        EXPORT_AND_IMPORT(0), ONLY_EXPORT(1), ONLY_IMPORT(2);
        int value;
        Type(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 标题，如订单编号
     */
    String title();

    /**
     * 序号，如1
     */
    int sort() default 0;

    /**
     * 字段类型
     */
    Type type() default Type.EXPORT_AND_IMPORT;
}