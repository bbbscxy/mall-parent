package com.vim.common.push;

/**
 * @作者 Administrator
 * @时间 2019-07-31 9:47
 * @版本 1.0
 * @说明 消息类型
 */
public class WebSocketMsg {

    public enum MsgType{
        ONLINE_COUNT, NOTIFY;
    }

    /**
     * 消息类型
     */
    private MsgType type;
    /**
     * 消息内容
     */
    private Object data;

    public WebSocketMsg(MsgType type, Object data) {
        this.type = type;
        this.data = data;
    }

    public MsgType getType() {
        return type;
    }

    public void setType(MsgType type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
