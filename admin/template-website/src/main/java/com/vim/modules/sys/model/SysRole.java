package com.vim.modules.sys.model;

import com.vim.common.base.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
* @作者 Administrator
* @时间 2019-07-19 12:21:31
* @版本 1.0
* @说明
*/
public class SysRole extends DataEntity {

    /**
    * 角色名称
    */
    @NotNull(message = "角色名称不能为空")
    @Length(min = 2, max = 40, message = "角色名称长度要在2-40之间")
    private String name;
    /**
    * 英文名称
    */
    @NotNull(message = "英文名称不能为空")
    @Length(min = 2, max = 40, message = "英文名称长度要在2-40之间")
    private String enname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

}