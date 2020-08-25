package com.vim.modules.sys.dao;

import com.vim.common.base.CrudDao;
import com.vim.modules.sys.model.SysDict;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @作者 Administrator
* @时间 2019-07-23 16:30:56
* @版本 1.0
* @说明 系统字典数据层
*/
@Repository
public interface SysDictDao extends CrudDao<SysDict>{

    /**
     * 根据code删除字典(不删除父级)
     * @param code
     */
    void deleteByCode(String code);

    /**
     * 根据code删除字典
     * @param code
     */
    void deleteAllByCode(String code);

    /**
     * 根据code查找字典
     * @param code
     */
    List<SysDict> getDictListByCode(String code);
}