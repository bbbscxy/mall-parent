package com.vim.common.queue;

import com.vim.common.utils.UserAgentUtils;
import com.vim.common.utils.UserUtils;
import com.vim.modules.sys.model.SysLoginLog;
import com.vim.modules.sys.service.SysLoginLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * @作者 Administrator
 * @时间 2019-07-30 17:07
 * @版本 1.0
 * @说明 记录登录日志的线程
 */
@Component
public class LoginLogThread implements Runnable{

    private static final  Logger logger = LoggerFactory.getLogger(LoginLogThread.class);

    private static LoginLogThread thread;
    @PostConstruct
    public void init() {
        thread = this;
    }

    @Autowired
    private SysLoginLogService sysLoginLogService;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                SysLoginLog log = WorkQueue.loginQueue.take();
                thread.sysLoginLogService.save(log);
            }catch (Exception e){
                logger.error("系统异常!", e);
            }
        }
    }

    /**
     * 记录登录日志
     * @param request
     */
    public static void record(HttpServletRequest request){
        SysLoginLog log = new SysLoginLog();
        log.setUserId(UserUtils.currentUser().getId());
        log.setUserName(UserUtils.currentUser().getName());
        log.setRemoteIp(UserAgentUtils.getIpAddress(request));
        log.setUserAgent(UserAgentUtils.getUserAgent(request));
        try {
            WorkQueue.loginQueue.put(log);
        } catch (InterruptedException e) {
            logger.error("系统异常!", e);
        }
    }
}
