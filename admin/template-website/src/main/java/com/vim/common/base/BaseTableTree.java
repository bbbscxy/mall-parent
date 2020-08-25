package com.vim.common.base;

/**
 * @作者 Administrator
 * @时间 2019-08-20 9:02
 * @版本 1.0
 * @说明
 */
public class BaseTableTree {

    private Long id;
    private Long parentId;
    private Long level;
    private boolean leaf = true;
    private boolean expanded;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
