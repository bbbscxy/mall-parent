package com.vim.modules.sys.service;

import com.vim.common.base.CrudService;
import com.vim.common.utils.EncryUtils;
import com.vim.common.utils.UserUtils;
import com.vim.modules.sys.dao.SysDeptDao;
import com.vim.modules.sys.model.SysDept;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @作者 Administrator
* @时间 2019-08-11 10:24:36
* @版本 1.0
* @说明
*/
@Service
public class SysDeptService extends CrudService<SysDeptDao, SysDept> {

    @Override
    public void save(SysDept entity) {
        Date date = new Date();
        entity.setId(EncryUtils.numPrimaryKey());
        entity.setCreateBy(UserUtils.currentUser().getId());
        entity.setCreateDate(date);
        entity.setUpdateBy(UserUtils.currentUser().getId());
        entity.setUpdateDate(date);
        dao.save(entity);
    }

}