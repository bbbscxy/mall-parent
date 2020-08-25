package com.vim.modules.sys.model;

import com.vim.common.base.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @作者 Administrator
 * @时间 2019-07-31 13:54:30
 * @版本 1.0
 * @说明
 */
public class SysMsg extends DataEntity {

    /**
     * 标题
     */
    @NotNull(message = "标题名称不能为空")
    @Length(max = 100, message = "标题最大长度为100")
    private String title;
    /**
     * 链接
     */
    private String href;
    /**
     * 推送状态（0未推送 1已推送）
     */
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}