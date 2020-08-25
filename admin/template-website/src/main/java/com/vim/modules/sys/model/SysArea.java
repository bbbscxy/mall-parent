package com.vim.modules.sys.model;

import com.vim.common.base.DataEntity;

import java.util.List;

/**
* @作者 Administrator
* @时间 2019-08-11 14:46:01
* @版本 1.0
* @说明
*/
public class SysArea extends DataEntity {

    /**
    * 地区名称
    */
    private String name;
    /**
    * 父级编号
    */
    private String parentId;
    /**
     * 级别
     */
    private String level;
    /**
     * 子菜单列表
     */
    private List<SysArea> childList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<SysArea> getChildList() {
        return childList;
    }

    public void setChildList(List<SysArea> childList) {
        this.childList = childList;
    }
}