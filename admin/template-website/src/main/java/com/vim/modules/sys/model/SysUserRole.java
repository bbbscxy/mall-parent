package com.vim.modules.sys.model;

import com.vim.common.base.DataEntity;

/**
* @作者 Administrator
* @时间 2019-07-19 12:21:31
* @版本 1.0
* @说明
*/
public class SysUserRole extends DataEntity {

    /**
    * 用户编号
    */
    private String userId;
    /**
    * 角色编号
    */
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}