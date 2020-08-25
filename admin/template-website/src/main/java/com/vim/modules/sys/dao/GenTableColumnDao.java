package com.vim.modules.sys.dao;

import com.vim.common.base.CrudDao;
import com.vim.modules.sys.model.GenTableColumn;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @作者 Administrator
* @时间 2019-07-25 14:42:10
* @版本 1.0
* @说明
*/
@Repository
public interface GenTableColumnDao extends CrudDao<GenTableColumn>{

    /**
     * 获取表中的所有字段
     */
    List<GenTableColumn> tableColumnList(String tableName);

    /**
     * 清空表数据
     */
    void clear();
}