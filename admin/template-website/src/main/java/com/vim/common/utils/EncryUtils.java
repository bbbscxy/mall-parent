package com.vim.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @作者 Administrator
 * @时间 2019-07-18 20:06
 * @版本 1.0
 * @说明 加解密
 */
public class EncryUtils {

    private EncryUtils(){}

    private static final Logger logger = LoggerFactory.getLogger(EncryUtils.class);

    private static int seq = 0;
    private static final int ROTATION = 99;

    /**
     * 密码加密
     * @param loginName 账号
     * @param password  密码
     */
    public static String encryPassword(String loginName, String password){
        int hashIterations = 1024;//加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes(loginName);
        SimpleHash hash = new SimpleHash("MD5", password, credentialsSalt, hashIterations);
        return hash.toString();
    }

    /**
     * 主键生成策略
     */
    public static String primaryKey(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 主键生成策略(纯数字)
     * @return
     */
    public static String numPrimaryKey(){
        if (seq > ROTATION) seq = 0;
        Date date = new Date();
        return String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$02d", date, seq++);
    }

    /**
     * ftp目录：根据日期生成
     */
    public static String ftpDateDir(){
        Calendar calendar = Calendar.getInstance();
        Integer YEAR = calendar.get(Calendar.YEAR);
        Integer MONTH = calendar.get(Calendar.MONTH);
        Integer DATE = calendar.get(Calendar.DAY_OF_MONTH);
        return YEAR+"/"+MONTH+"/"+DATE+"/";
    }

}
