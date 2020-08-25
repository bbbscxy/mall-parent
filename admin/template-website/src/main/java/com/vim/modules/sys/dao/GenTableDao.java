package com.vim.modules.sys.dao;

import com.vim.common.base.CrudDao;
import com.vim.modules.sys.model.GenTable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @作者 Administrator
* @时间 2019-07-25 14:42:10
* @版本 1.0
* @说明
*/
@Repository
public interface GenTableDao extends CrudDao<GenTable>{

    /**
     * 查询所有的表
     */
    List<GenTable> tableList();

    /**
     * 清空表数据
     */
    void clear();
}