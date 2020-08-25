package com.vim.common.push;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @作者 Administrator
 * @时间 2019-07-31 9:07
 * @版本 1.0
 * @说明 websocket 服务
 */
@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocketServer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private Session session;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 在线人数
     */
    private static AtomicLong onlineCount = new AtomicLong(0);
    /**
     * 客户端集合
     */
    private static HashSet<WebSocketServer> servers = new HashSet<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        boolean online = false;
        for(WebSocketServer server:servers){
            if(server.getUserId().equals(userId)){
                online = true;
            }
        }
        if(!servers.contains(this) && !online){
            servers.add(this);
            onlineCount.incrementAndGet();
            sendMessage(new WebSocketMsg(WebSocketMsg.MsgType.ONLINE_COUNT, onlineCount.get()));
        }
    }

    @OnClose
    public void onClose() {
        servers.remove(this);
        onlineCount.decrementAndGet();
        sendMessage(new WebSocketMsg(WebSocketMsg.MsgType.ONLINE_COUNT, onlineCount.get()));
    }

    /**
     * 发送消息到指定用户
     */
    public static void sendMessage(WebSocketMsg message, String userId) {
        for (WebSocketServer server : servers) {
            try {
                if(server.getUserId().equals(userId)){
                    server.session.getBasicRemote().sendText(JSONObject.toJSONString(message));
                }
            } catch (Exception e) {
                logger.error("系统异常!", e);
            }
        }
    }

    /**
     * 群发消息
     */
    public static void sendMessage(WebSocketMsg message) {
        for (WebSocketServer server : servers) {
            try {
                server.session.getBasicRemote().sendText(JSONObject.toJSONString(message));
            } catch (Exception e) {
                logger.error("系统异常!", e);
            }
        }
    }
}
