package com.vim.modules.sys.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vim.common.base.BaseTableTree;

import java.util.Date;

/**
 * @作者 Administrator
 * @时间 2019-07-23 9:46
 * @版本 1.0
 * @说明
 */
public class SysDeptTree extends BaseTableTree {

    /**
     * 部门名称
     */
    private String name;
    /**
     * 负责人名称
     */
    private String leaderName;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
