package com.vim.common.convert;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @作者 Administrator
 * @时间 2019-08-17 10:23
 * @版本 1.0
 * @说明
 */
public class DateConverter implements Converter<String, Date> {

    private static final Logger logger = LoggerFactory.getLogger(DateConverter.class);

    private SimpleDateFormat sdDate = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date convert(String s) {
        if(StringUtils.isBlank(s)){
            return null;
        }
        Date date = null;
        try {
            date = sdDate.parse(s);
        } catch (Exception e) {
            logger.error("日期类型转化异常!", e);
            try {
                date = sdDateTime.parse(s);
            } catch (ParseException e1) {
                logger.error("日期类型转化异常!", e1);
            }
        }
        return date;
    }
}
