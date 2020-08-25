package com.vim.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @作者 Administrator
 * @时间 2019-08-08 11:49
 * @版本 1.0
 * @说明 cookie操作
 */
public class CookieUtils {

    private CookieUtils(){}

    /**
     * 设置cookie
     */
    public static void setCookie(HttpServletResponse response, String name, String value){
        setCookie(response, name, value, -1);
    }

    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 查询cookie
     */
    public static String getCookie(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(name)){
                return cookie.getValue();
            }
        }
        return "";
    }
}
