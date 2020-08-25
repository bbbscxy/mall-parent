package com.vim.modules.sys.model;

import com.vim.common.base.DataEntity;

/**
 * @作者 Administrator
 * @时间 2019-07-31 16:35:31
 * @版本 1.0
 * @说明
 */
public class SysUserMsg extends DataEntity {

    /**
     * 消息编号
     */
    private String msgId;
    private String title;
    private SysMsg sysMsg;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 阅读状态（0未阅读 1已阅读）
     */
    private String status;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SysMsg getSysMsg() {
        return sysMsg;
    }

    public void setSysMsg(SysMsg sysMsg) {
        this.sysMsg = sysMsg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}