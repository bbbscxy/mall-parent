package com.vim.common.queue;

import com.alibaba.fastjson.JSON;
import com.vim.common.utils.UserAgentUtils;
import com.vim.common.utils.UserUtils;
import com.vim.modules.sys.model.SysOperateLog;
import com.vim.modules.sys.service.SysOperateLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @作者 Administrator
 * @时间 2019-07-30 17:07
 * @版本 1.0
 * @说明 记录操作日志的线程
 */
@Component
public class OperateLogThread implements Runnable{

    private static final  Logger logger = LoggerFactory.getLogger(OperateLogThread.class);

    private static OperateLogThread thread;
    @PostConstruct
    public void init() {
        thread = this;
    }

    @Autowired
    private SysOperateLogService sysOperateLogService;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                SysOperateLog log = WorkQueue.operateQueue.take();
                thread.sysOperateLogService.save(log);
            }catch (Exception e){
                logger.error("系统异常!", e);
            }
        }
    }

    /**
     * 记录操作日志
     * @param request
     */
    public static void record(HttpServletRequest request){
        SysOperateLog log = new SysOperateLog();
        log.setUserId(UserUtils.currentUser().getId());
        log.setUserName(UserUtils.currentUser().getName());
        log.setRemoteIp(UserAgentUtils.getIpAddress(request));
        log.setUserAgent(UserAgentUtils.getUserAgent(request));
        log.setRequestUri(request.getRequestURI());
        log.setMethod(request.getMethod());
        log.setParams(JSON.toJSONString(getRequestParams(request)));
        log.setTitle("");
        try {
            WorkQueue.operateQueue.put(log);
        } catch (InterruptedException e) {
            logger.error("系统异常!", e);
        }
    }

    private static Map<String, String> getRequestParams(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                //如果字段的值为空，判断若值为空，则删除这个字段>
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }
}
