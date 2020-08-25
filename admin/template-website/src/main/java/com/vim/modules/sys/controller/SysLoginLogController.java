package com.vim.modules.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vim.common.base.BaseController;
import com.vim.modules.sys.model.SysLoginLog;
import com.vim.modules.sys.service.SysLoginLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @作者 Administrator
 * @时间 2019-07-30 15:38:20
 * @版本 1.0
 * @说明
 */
@Controller
@RequestMapping(value = "/sysLoginLog")
public class SysLoginLogController extends BaseController{

    @Autowired
    private SysLoginLogService sysLoginLogService;

    /**
     * 列表页面
     */
    @RequestMapping(value = {"", "/list"})
    public String list(){
        return "sys/loginLog/list";
    }

    /**
     * 列表数据
     */
    @RequiresPermissions(value = "sys:loginLog:list")
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public PageInfo<SysLoginLog> dataList(SysLoginLog sysLoginLog){
        PageHelper.startPage(sysLoginLog.getPageNum(), sysLoginLog.getPageSize());
        return sysLoginLogService.findPageList(sysLoginLog);
    }

}