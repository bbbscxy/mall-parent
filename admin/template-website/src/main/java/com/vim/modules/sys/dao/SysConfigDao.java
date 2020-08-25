package com.vim.modules.sys.dao;

import com.vim.common.base.CrudDao;
import com.vim.modules.sys.model.SysConfig;
import org.springframework.stereotype.Repository;

/**
* @作者 Administrator
* @时间 2019-07-23 16:16:54
* @版本 1.0
* @说明 系统配置数据层
*/
@Repository
public interface SysConfigDao extends CrudDao<SysConfig>{

    /**
     * 查询配置信息
     * @param code 配置标识
     */
    SysConfig findByCode(String code);

    /**
     * 根据code删除配置
     * @param code
     */
    void deleteByCode(String code);
}