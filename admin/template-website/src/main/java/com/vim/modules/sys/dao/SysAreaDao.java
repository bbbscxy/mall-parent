package com.vim.modules.sys.dao;

import com.vim.common.base.CrudDao;
import com.vim.modules.sys.model.SysArea;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @作者 Administrator
* @时间 2019-08-11 14:46:01
* @版本 1.0
* @说明
*/
@Repository
public interface SysAreaDao extends CrudDao<SysArea>{

    /**
     * 清空表数据
     */
    void clear();

    /**
     * 批量保存
     */
    void batchSave(@Param("areaList") List<SysArea> areaList);
}