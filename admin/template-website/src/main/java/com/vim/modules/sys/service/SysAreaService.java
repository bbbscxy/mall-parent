package com.vim.modules.sys.service;

import com.vim.common.base.CrudService;
import com.vim.modules.sys.dao.SysAreaDao;
import com.vim.modules.sys.model.SysArea;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
* @作者 Administrator
* @时间 2019-08-11 14:46:01
* @版本 1.0
* @说明
*/
@Service
public class SysAreaService extends CrudService<SysAreaDao, SysArea> {

    @Override
    public void save(SysArea entity) {
        Date date = new Date();
        entity.setCreateBy("1");
        entity.setCreateDate(date);
        entity.setUpdateBy("1");
        entity.setUpdateDate(date);
        dao.save(entity);
    }

    /**
     * 初始化区域数据
     * @param areaList
     */
    @Transactional
    public void initAreaData(List<SysArea> areaList) {
        //1.清空表数据
        dao.clear();
        //2.初始化数据
        dao.batchSave(areaList);
    }
}