package com.vim.modules.sys.model;

import com.vim.common.base.DataEntity;

import java.util.Date;

/**
 * @作者 Administrator
 * @时间 2019-08-17 10:46:38
 * @版本 1.0
 * @说明
 */
public class SysOperateLog extends DataEntity {

    /**
     * 用户编号
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * IP地址
     */
    private String remoteIp;
    /**
     * 用户代理
     */
    private String userAgent;
    /**
     * 操作标题
     */
    private String title;
    /**
     * 请求地址
     */
    private String requestUri;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String params;

    private Date startTime;
    private Date endTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }


    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}