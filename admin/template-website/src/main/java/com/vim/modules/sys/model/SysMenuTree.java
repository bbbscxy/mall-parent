package com.vim.modules.sys.model;

import com.vim.common.base.BaseTableTree;

/**
 * @作者 Administrator
 * @时间 2019-08-15 15:23
 * @版本 1.0
 * @说明
 */
public class SysMenuTree extends BaseTableTree {

    /**
     * 名称
     */
    private String name;
    /**
     * 排序
     */
    private String sort;
    /**
     * 链接
     */
    private String href;
    /**
     * 权限标识
     */
    private String permission;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
