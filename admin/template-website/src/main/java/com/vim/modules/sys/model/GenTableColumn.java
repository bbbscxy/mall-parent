package com.vim.modules.sys.model;

import com.vim.common.base.DataEntity;
import com.vim.common.utils.CapitalCamelUtils;

public class GenTableColumn extends DataEntity {

    private String tableId;     // 表编号
    private String name; 		// 列名
    private String displayName; // 显示名称
    private String comments;	// 描述
    private String jdbcType;	// JDBC类型
    private String length;      // 长度
    private String javaType;	// JAVA类型
    private String javaField;	// JAVA字段名
    private String javaFieldUpper; //JAVA字段名首字母大写
    private String isPk;		// 是否主键（1：主键）
    private String isNull;		// 是否可为空（1：可为空；0：不为空）
    private String isList;		// 是否列表字段（1：列表字段）
    private String isEdit;      // 是否编辑字段（1：编辑字段）
    private String isQuery;		// 是否查询字段（1：查询字段）
    private String queryType;	// 查询方式（等于、LIKE）
    private String showType;	// 字段生成方案（文本框、文本域、字典下拉）
    private String dictType;	// 字典类型
    private String sort;        // 排序

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        if(org.apache.commons.lang3.StringUtils.isNotBlank(displayName)){
            return displayName;
        }else{
            return comments;
        }
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaField() {
        return CapitalCamelUtils.toCamelCase(this.name);
    }

    public void setJavaField(String javaField) {
        this.javaField = javaField;
    }

    public String getJavaFieldUpper() {
        return CapitalCamelUtils.toCapitalizeCamelCase(this.name);
    }

    public void setJavaFieldUpper(String javaFieldUpper) {
        this.javaFieldUpper = javaFieldUpper;
    }

    public String getIsPk() {
        return isPk;
    }

    public void setIsPk(String isPk) {
        this.isPk = isPk;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getIsList() {
        return isList;
    }

    public void setIsList(String isList) {
        this.isList = isList;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getIsQuery() {
        return isQuery;
    }

    public void setIsQuery(String isQuery) {
        this.isQuery = isQuery;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
