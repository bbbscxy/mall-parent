package com.vim.modules.sys.service;

import com.vim.common.base.CrudService;
import com.vim.common.constants.SysDictConstants;
import com.vim.common.queue.WorkQueue;
import com.vim.modules.sys.dao.SysMsgDao;
import com.vim.modules.sys.model.SysMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @作者 Administrator
 * @时间 2019-07-31 12:39:43
 * @版本 1.0
 * @说明
 */
@Service
public class SysMsgService extends CrudService<SysMsgDao, SysMsg> {

    private static final Logger logger = LoggerFactory.getLogger(SysMsgService.class);

    @Override
    public void save(SysMsg entity) {
        entity.setStatus(SysDictConstants.MSG_PUSH_STATUS.NO.getValue());
        super.save(entity);
    }

    /**
     * 推送消息
     * @param id
     */
    public void pushMsg(String id) {
        SysMsg msg = get(id);
        try {
            WorkQueue.pushQueue.put(msg);
        } catch (InterruptedException e) {
            logger.error("系统异常!", e);
        }
        msg.setStatus(SysDictConstants.MSG_PUSH_STATUS.YES.getValue());
        super.update(msg);
    }
}