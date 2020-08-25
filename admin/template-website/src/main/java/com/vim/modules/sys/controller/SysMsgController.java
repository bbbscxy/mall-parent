package com.vim.modules.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vim.common.base.BaseController;
import com.vim.modules.sys.model.SysMsg;
import com.vim.modules.sys.service.SysMsgService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @作者 Administrator
 * @时间 2019-07-31 12:39:43
 * @版本 1.0
 * @说明
 */
@Controller
@RequestMapping(value = "/sysMsg")
public class SysMsgController extends BaseController{

    @Autowired
    private SysMsgService sysMsgService;

    /**
     * 列表页面
     */
    @RequiresPermissions(value = "sys:msg:list")
    @RequestMapping(value = {"", "/list"})
    public String list(){
        return "sys/msg/list";
    }

    /**
     * 列表数据
     */
    @RequiresPermissions(value = "sys:msg:list")
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public PageInfo<SysMsg> dataList(SysMsg sysMsg){
        PageHelper.startPage(sysMsg.getPageNum(), sysMsg.getPageSize());
        return sysMsgService.findPageList(sysMsg);
    }

    /**
     * 编辑页面
     */
    @RequiresPermissions(value = "sys:msg:form")
    @RequestMapping(value = "/form")
    public String form(SysMsg sysMsg, Model model){
        model.addAttribute("sysMsg", StringUtils.isNoneBlank(sysMsg.getId()) ? sysMsgService.get(sysMsg.getId()) : new SysMsg());
        return "sys/msg/form";
    }

    /**
     * 更新数据
     */
    @RequiresPermissions(value = "sys:msg:form")
    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save(@Validated SysMsg sysMsg){
        sysMsgService.saveOrUpdate(sysMsg);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    /**
     * 删除
     */
    @RequiresPermissions(value = "sys:msg:delete")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> delete(SysMsg sysMsg){
        sysMsgService.delete(sysMsg.getId());
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    /**
     * 推送
     */
    @RequiresPermissions(value = "sys:msg:push")
    @PostMapping(value = "/push")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> push(SysMsg sysMsg){
        sysMsgService.pushMsg(sysMsg.getId());
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }
}