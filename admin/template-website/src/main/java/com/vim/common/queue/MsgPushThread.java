package com.vim.common.queue;

import com.vim.common.constants.SysDictConstants;
import com.vim.common.push.WebSocketMsg;
import com.vim.common.push.WebSocketServer;
import com.vim.modules.sys.model.SysMsg;
import com.vim.modules.sys.model.SysUser;
import com.vim.modules.sys.model.SysUserMsg;
import com.vim.modules.sys.service.SysUserMsgService;
import com.vim.modules.sys.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.vim.common.constants.Global.USER_ADMIN_ID;

/**
 * @作者 Administrator
 * @时间 2019-07-31 15:03
 * @版本 1.0
 * @说明 消息推送线程
 */
@Component
public class MsgPushThread implements Runnable {

    private static final  Logger logger = LoggerFactory.getLogger(MsgPushThread.class);

    private static MsgPushThread thread;
    @PostConstruct
    public void init() {
        thread = this;
    }

    @Autowired
    private SysUserMsgService sysUserMsgService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public void run() {
        while (true){
            try {
                SysMsg msg = WorkQueue.pushQueue.take();
                //1.保存数据库
                List<SysUser> userList = thread.sysUserService.findList(new SysUser());
                for(SysUser user:userList){
                    if(!USER_ADMIN_ID.equals(user.getId())){
                        SysUserMsg userMsg = new SysUserMsg();
                        userMsg.setMsgId(msg.getId());
                        userMsg.setUserId(user.getId());
                        userMsg.setStatus(SysDictConstants.MSG_READ_STATUS.NO.getValue());
                        thread.sysUserMsgService.save(userMsg);
                    }
                }
                //2.推送给当前的登录用户
                WebSocketServer.sendMessage(new WebSocketMsg(WebSocketMsg.MsgType.NOTIFY, msg));
            }catch (Exception e){
                logger.error("系统异常!", e);
            }
        }
    }
}
