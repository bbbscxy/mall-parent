package com.vim.modules.sys.model;

import com.vim.common.base.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
* @作者 Administrator
* @时间 2019-07-23 16:30:56
* @版本 1.0
* @说明
*/
public class SysDict extends DataEntity {

    /**
    * 字典标签
    */
    @NotNull(message = "字典标签不能为空")
    @Length(min = 2, max = 40, message = "字典标签长度要在2-40之间")
    private String label;
    /**
     * 字典类型
     */
    @NotNull(message = "字典类型不能为空")
    @Length(min = 2, max = 40, message = "字典类型长度要在2-40之间")
    private String code;
    /**
    * 字典名称
    */
    @NotNull(message = "字典名称不能为空")
    @Length(min = 1, max = 40, message = "字典名称长度要在1-40之间")
    private String name;
    /**
    * 字典值(message = "字典标签不能为空")
    */
    @NotNull(message = "字典值不能为空")
    @Length(min = 1, max = 100, message = "字典值长度要在1-100之间")
    private String value;
    /**
    * 排序（升序）
    */
    private String sort;
    /**
     * 是否父级： 0不是 1是
     */
    private String isParent;
    /**
     * 模块类型： 0系统字典  1业务字典
     */
    private String type;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}