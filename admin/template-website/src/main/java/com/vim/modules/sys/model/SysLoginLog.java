package com.vim.modules.sys.model;

import com.vim.common.base.DataEntity;

import java.util.Date;

/**
 * @作者 Administrator
 * @时间 2019-07-30 15:38:20
 * @版本 1.0
 * @说明
 */
public class SysLoginLog extends DataEntity {

    /**
     * 用户编号
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 操作IP地址
     */
    private String remoteIp;
    /**
     * 操作地址
     */
    private String remoteAddress;
    /**
     * 用户代理
     */
    private String userAgent;

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

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}