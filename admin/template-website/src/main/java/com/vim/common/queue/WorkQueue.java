package com.vim.common.queue;

import com.vim.modules.sys.model.SysLoginLog;
import com.vim.modules.sys.model.SysMsg;
import com.vim.modules.sys.model.SysOperateLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @作者 Administrator
 * @时间 2019-07-30 15:14
 * @版本 1.0
 * @说明 工作队列
 */
@Component
public class WorkQueue {

    private static WorkQueue workQueue;
    @PostConstruct
    public void init() {
        workQueue = this;
    }

    @Value("${login.log.thread.num}")
    private int loginLogThreadNum;
    @Value("${operate.log.thread.num}")
    private int operateLogThreadNum;
    @Value("${msg.push.thread.num}")
    private int msgPushThreadNum;

    //登录日志队列
    public static final LinkedBlockingQueue<SysLoginLog> loginQueue = new LinkedBlockingQueue<>();
    //操作日志队列
    public static final LinkedBlockingQueue<SysOperateLog> operateQueue = new LinkedBlockingQueue<>();
    //推送消息队列
    public static final LinkedBlockingQueue<SysMsg> pushQueue = new LinkedBlockingQueue<>();

    //初始化登录日志线程
    public static void initLoginLogThread(){
        ExecutorService service = Executors.newFixedThreadPool(workQueue.loginLogThreadNum, new ThreadFactory() {
            private AtomicInteger atomic = new AtomicInteger();
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "LoginLogThread-" + this.atomic.getAndIncrement());
            }
        });
        for(int i=0; i<workQueue.loginLogThreadNum; i++){
            service.submit(new LoginLogThread());
        }
    }

    //初始化操作日志线程
    public static void initOperateLogThread(){
        ExecutorService service = Executors.newFixedThreadPool(workQueue.operateLogThreadNum, new ThreadFactory() {
            private AtomicInteger atomic = new AtomicInteger();
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "OperateLogThread-" + this.atomic.getAndIncrement());
            }
        });
        for(int i=0; i<workQueue.operateLogThreadNum; i++){
            service.submit(new OperateLogThread());
        }
    }

    //初始化消息推送线程
    public static void initMsgPushThread(){
        ExecutorService service = Executors.newFixedThreadPool(workQueue.msgPushThreadNum, new ThreadFactory() {
            private AtomicInteger atomic = new AtomicInteger();
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "MsgPushThread-" + this.atomic.getAndIncrement());
            }
        });
        for(int i=0; i<workQueue.msgPushThreadNum; i++){
            service.submit(new MsgPushThread());
        }
    }
}
