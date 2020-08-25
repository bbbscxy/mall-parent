package com.vim.modules.sys.model;

import com.vim.common.base.DataEntity;

/**
* @作者 Administrator
* @时间 2019-07-19 12:21:31
* @版本 1.0
* @说明
*/
public class SysRoleMenu extends DataEntity {

    /**
    * 角色编号
    */
    private String roleId;
    /**
    * 菜单编号
    */
    private String menuId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

}