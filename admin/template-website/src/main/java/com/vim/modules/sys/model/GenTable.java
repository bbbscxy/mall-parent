package com.vim.modules.sys.model;

import com.vim.common.base.DataEntity;

public class GenTable extends DataEntity {

    private String name; 	// 名称
    private String comments;		// 描述

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
