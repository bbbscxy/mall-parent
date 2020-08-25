package com.vim.modules.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vim.common.base.BaseController;
import com.vim.common.exception.BusinessException;
import com.vim.modules.sys.model.SysConfig;
import com.vim.modules.sys.service.SysConfigService;
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
* @时间 2019-07-23 16:30:55
* @版本 1.0
* @说明 系统配置控制层
*/
@Controller
@RequestMapping(value = "/sysConfig")
public class SysConfigController extends BaseController{

    @Autowired
    private SysConfigService sysConfigService;

    /**
    * 列表页面
    */
    @RequiresPermissions(value = "sys:config:list")
    @RequestMapping(value = {"", "/list"})
    public String list(){
        return "sys/config/list";
    }

    /**
    * 列表数据
    */
    @RequiresPermissions(value = "sys:config:list")
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public PageInfo<SysConfig> dataList(SysConfig sysConfig){
        PageHelper.startPage(sysConfig.getPageNum(), sysConfig.getPageSize());
        return sysConfigService.findPageList(sysConfig);
    }

    /**
    * 编辑页面
    */
    @RequiresPermissions(value = "sys:config:form")
    @RequestMapping(value = "/form")
    public String form(SysConfig sysConfig, Model model){
        model.addAttribute("sysConfig", StringUtils.isNoneBlank(sysConfig.getId()) ? sysConfigService.get(sysConfig.getId()) : new SysConfig());
        return "sys/config/form";
    }

    /**
    * 更新数据
    */
    @RequiresPermissions(value = "sys:config:form")
    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save(@Validated SysConfig sysConfig) throws Exception{
        if(StringUtils.isBlank(sysConfig.getId()) && sysConfigService.check(sysConfig.getCode())){
            throw new BusinessException("配置标识已存在!");
        }
        sysConfigService.saveOrUpdate(sysConfig);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    /**
    * 删除
    */
    @RequiresPermissions(value = "sys:config:delete")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> delete(SysConfig sysConfig){
        SysConfig config = sysConfigService.get(sysConfig.getId());
        sysConfigService.deleteByCode(config.getCode());
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }
}