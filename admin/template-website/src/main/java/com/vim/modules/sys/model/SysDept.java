package com.vim.modules.sys.model;

import com.vim.common.base.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
* @作者 Administrator
* @时间 2019-08-11 10:24:36
* @版本 1.0
* @说明
*/
public class SysDept extends DataEntity {

    /**
    * 部门名称
    */
    @NotNull(message = "部门名称不能为空")
    @Length(min = 2, max = 40, message = "部门名称长度要在2-40之间")
    private String deptName;
    /**
    * 负责人
    */
    private String leaderId;
    /**
    * 负责人名称
    */
    private String leaderName;
    /**
    * 父级编号
    */
    @NotNull(message = "父级编号不能为空")
    private String parentId;
    /**
    * 所有父级编号
    */
    @NotNull(message = "父级编号不能为空")
    private String parentIds;
    /**
    * 排序
    */
    private String sort;
    /**
    * 图标
    */
    private String icon;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


}