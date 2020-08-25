package com.vim.modules.sys.model;

/**
 * @作者 Administrator
 * @时间 2019-07-22 17:25
 * @版本 1.0
 * @说明 zTree 节点
 */
public class ZTreeNode {

    private long id;
    private long pId;
    private String pIds;
    private String name;

    private String icon;

    private boolean open;
    private boolean checked;

    private String isParent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public String getpIds() {
        return pIds;
    }

    public void setpIds(String pIds) {
        this.pIds = pIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }
}

