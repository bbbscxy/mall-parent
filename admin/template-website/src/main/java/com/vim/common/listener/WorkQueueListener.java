package com.vim.common.listener;

import com.vim.common.queue.WorkQueue;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @作者 Administrator
 * @时间 2019-07-30 15:48
 * @版本 1.0
 * @说明 线程队列启动监听器
 */
@Component
public class WorkQueueListener implements ApplicationListener<ContextRefreshedEvent> {

    private static WorkQueueListener workQueueListener;
    @PostConstruct
    public void init() {
        workQueueListener = this;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //启动登录日志记录线程
        WorkQueue.initLoginLogThread();
        //启动操作日志记录线程
        WorkQueue.initOperateLogThread();
        //启动消息推送处理线程
        WorkQueue.initMsgPushThread();
    }

}
