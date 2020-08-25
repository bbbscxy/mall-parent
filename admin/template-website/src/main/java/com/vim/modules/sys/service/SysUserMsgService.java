package com.vim.modules.sys.service;

import com.vim.common.base.CrudService;
import com.vim.common.constants.SysDictConstants;
import com.vim.modules.sys.dao.SysUserMsgDao;
import com.vim.modules.sys.model.SysUserMsg;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @作者 Administrator
 * @时间 2019-07-31 16:35:31
 * @版本 1.0
 * @说明
 */
@Service
public class SysUserMsgService extends CrudService<SysUserMsgDao, SysUserMsg> {

    /**
     * 已读
     * @param msgList
     */
    @Transactional
    public void read(List<String> msgList) {
        for(String id:msgList){
            SysUserMsg msg = get(id);
            msg.setStatus(SysDictConstants.MSG_READ_STATUS.YES.getValue());
            update(msg);
        }
    }
}