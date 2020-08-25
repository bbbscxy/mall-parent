package com.vim.modules.sys.model;

import com.vim.common.base.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
* @作者 Administrator
* @时间 2019-07-19 12:21:31
* @版本 1.0
* @说明
*/
public class SysMenu extends DataEntity {

    /**
    * 父级编号
    */
    private String parentId;
    /**
    * 所有父级编号
    */
    private String parentIds;
    /**
    * 名称
    */
    @NotNull(message = "菜单名称不能为空")
    @Length(min = 2, max = 40, message = "菜单名称长度要在2-40之间")
    private String name;
    /**
    * 排序
    */
    private String sort;
    /**
    * 链接
    */
    @Length(max = 100, message = "链接最大长度为100")
    private String href;
    /**
    * 图标
    */
    private String icon;
    /**
    * 是否在菜单中显示
    */
    private String isShow;
    /**
    * 权限标识
    */
    @Length(max = 100, message = "权限标识最大长度为100")
    private String permission;

    /**
     * 子菜单列表
     */
    private List<SysMenu> childMenuList;

    public List<SysMenu> getChildMenuList() {
        return childMenuList;
    }

    public void setChildMenuList(List<SysMenu> childMenuList) {
        this.childMenuList = childMenuList;
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
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

}