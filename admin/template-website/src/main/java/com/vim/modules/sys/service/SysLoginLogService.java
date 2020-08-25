package com.vim.modules.sys.service;

import com.vim.common.base.CrudService;
import com.vim.common.constants.Global;
import com.vim.common.utils.EncryUtils;
import com.vim.modules.sys.dao.SysLoginLogDao;
import com.vim.modules.sys.model.SysLoginLog;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @作者 Administrator
 * @时间 2019-07-30 15:38:20
 * @版本 1.0
 * @说明
 */
@Service
public class SysLoginLogService extends CrudService<SysLoginLogDao, SysLoginLog> {

    @Override
    public void save(SysLoginLog entity) {
        Date date = new Date();
        entity.setId(EncryUtils.primaryKey());
        entity.setCreateBy(Global.USER_ADMIN_ID);
        entity.setCreateDate(date);
        entity.setUpdateBy(Global.USER_ADMIN_ID);
        entity.setUpdateDate(date);
        dao.save(entity);
    }
}
