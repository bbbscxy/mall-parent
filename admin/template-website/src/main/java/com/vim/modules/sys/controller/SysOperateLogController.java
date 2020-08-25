package com.vim.modules.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vim.common.base.BaseController;
import com.vim.modules.sys.model.SysOperateLog;
import com.vim.modules.sys.service.SysOperateLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @作者 Administrator
* @时间 2019-08-17 10:36:11
* @版本 1.0
* @说明
*/
@Controller
@RequestMapping(value = "/sysOperateLog")
public class SysOperateLogController extends BaseController{

    @Autowired
    private SysOperateLogService sysOperateLogService;

    /**
    * 列表页面
    */
    @RequiresPermissions(value = "sys:operateLog:list")
    @RequestMapping(value = {"", "/list"})
    public String list(){
        return "sys/operateLog/list";
    }

    /**
     * 请求参数页面
     */
    @RequiresPermissions(value = "sys:operateLog:list")
    @RequestMapping(value = "/params")
    public String params(SysOperateLog sysOperateLog, Model model){
        model.addAttribute("params", sysOperateLogService.get(sysOperateLog.getId()).getParams());
        return "sys/operateLog/params";
    }

    /**
    * 列表数据
    */
    @RequiresPermissions(value = "sys:operateLog:list")
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public PageInfo<SysOperateLog> dataList(SysOperateLog sysOperateLog){
        PageHelper.startPage(sysOperateLog.getPageNum(), sysOperateLog.getPageSize());
        return sysOperateLogService.findPageList(sysOperateLog);
    }

}