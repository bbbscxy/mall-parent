package com.vim.modules.sys.model;

import com.vim.common.base.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
* @作者 Administrator
* @时间 2019-07-23 16:16:54
* @版本 1.0
* @说明 系统配置
*/
public class SysConfig extends DataEntity {

    /**
    * 配置标识
    */
    @NotNull(message = "配置标识不能为空")
    @Length(min = 2, max = 40, message = "配置标识长度要在2-40之间")
    private String code;
    /**
    * 配置名称
    */
    @NotNull(message = "配置名称不能为空")
    @Length(min = 2, max = 40, message = "配置名称长度要在2-40之间")
    private String name;
    /**
    * 配置值
    */
    @NotNull(message = "配置值不能为空")
    @Length(max = 100, message = "配置值最大长度为100")
    private String value;

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

}