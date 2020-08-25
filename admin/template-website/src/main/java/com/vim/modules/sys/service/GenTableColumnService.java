package com.vim.modules.sys.service;

import com.vim.common.base.CrudService;
import com.vim.modules.sys.dao.GenTableColumnDao;
import com.vim.modules.sys.model.GenTableColumn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @作者 Administrator
* @时间 2019-07-25 14:42:10
* @版本 1.0
* @说明
*/
@Service
public class GenTableColumnService extends CrudService<GenTableColumnDao, GenTableColumn> {

    /**
     * 清空表数据
     */
    public void clear() {
        dao.clear();
    }

    /**
     * 获取表中的所有字段
     * @param tableName 表名
     */
    public List<GenTableColumn> tableColumnList(String tableName) {
        return dao.tableColumnList(tableName);
    }

    /**
     * 更新字段生成策略
     * @param column
     */
    @Transactional
    public void updateFieldPolicy(GenTableColumn column) {
        GenTableColumn findColumn = new GenTableColumn();
        findColumn.setTableId(column.getTableId());
        List<GenTableColumn> tableColumnList = findList(findColumn);
        //更新每个字段生成策略
        String[] isList = column.getIsList().split(",");
        String[] isEdit = column.getIsEdit().split(",");
        String[] isQuery = column.getIsQuery().split(",");
        String[] queryType = column.getQueryType().split(",");
        String[] showType = column.getShowType().split(",");
        String[] dictType = column.getDictType().split(",", -1);
        String[] displayName = column.getDisplayName().split(",", -1);
        if(isList.length == tableColumnList.size()){
            for(int i=0; i<tableColumnList.size(); i++){
                GenTableColumn tableColumn = tableColumnList.get(i);
                tableColumn.setIsList(isList[i]);
                tableColumn.setIsEdit(isEdit[i]);
                tableColumn.setIsQuery(isQuery[i]);
                tableColumn.setQueryType(queryType[i]);
                tableColumn.setShowType(showType[i]);
                tableColumn.setDictType(dictType[i]);
                tableColumn.setDisplayName(displayName[i]);
                super.update(tableColumn);
            }
        }
    }
}