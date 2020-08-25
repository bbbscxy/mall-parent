package com.vim.modules.sys.model;

import com.vim.common.base.BaseTableTree;

/**
 * @作者 Administrator
 * @时间 2019-08-15 15:23
 * @版本 1.0
 * @说明
 */
public class SysAreaTree extends BaseTableTree {

    /**
     * 地区名称
     */
    private String name;
    /**
     * 地区编号
     */
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
