package com.vim.modules.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vim.common.base.BaseController;
import com.vim.common.constants.SysDictConstants;
import com.vim.common.exception.BusinessException;
import com.vim.modules.sys.model.SysDict;
import com.vim.modules.sys.service.SysDictService;
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

import java.util.ArrayList;
import java.util.Map;

/**
* @作者 Administrator
* @时间 2019-07-23 16:30:56
* @版本 1.0
* @说明 系统字典控制层
*/
@Controller
@RequestMapping(value = "/sysDict")
public class SysDictController extends BaseController{

    @Autowired
    private SysDictService sysDictService;

    /**
    * 列表页面
    */
    @RequiresPermissions(value = "sys:dict:list")
    @RequestMapping(value = {"", "/list"})
        public String list(){
        return "sys/dict/list";
    }

    /**
    * 列表数据
    */
    @RequiresPermissions(value = "sys:dict:list")
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public PageInfo<SysDict> dataList(SysDict sysDict){
        PageHelper.startPage(sysDict.getPageNum(), sysDict.getPageSize());
        sysDict.setIsParent(SysDictConstants.DICT_IS_PARENT.YES.getValue());
        return sysDictService.findPageList(sysDict);
    }

    /**
    * 编辑页面
    */
    @RequiresPermissions(value = "sys:dict:form")
    @RequestMapping(value = "/form")
    public String form(SysDict sysDict, Model model){
        if(StringUtils.isNoneBlank(sysDict.getId())){
            sysDict = sysDictService.get(sysDict.getId());
            SysDict findDict = new SysDict();
            findDict.setCode(sysDict.getCode());
            findDict.setIsParent(SysDictConstants.DICT_IS_PARENT.NO.getValue());
            model.addAttribute("sysDictChildList", sysDictService.findList(findDict));
            model.addAttribute("sysDict", sysDict);
        }else{
            model.addAttribute("sysDictChildList", new ArrayList<>());
            model.addAttribute("sysDict", new SysDict());
        }
        return "sys/dict/form";
    }

    /**
    * 更新数据
    */
    @RequiresPermissions(value = "sys:dict:form")
    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save(@Validated SysDict sysDict) throws Exception{
        if(StringUtils.isBlank(sysDict.getId()) && sysDictService.check(sysDict.getCode())){
            throw new BusinessException("字典已存在!");
        }
        sysDictService.saveOrUpdate(sysDict);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    /**
    * 删除
    */
    @RequiresPermissions(value = "sys:dict:delete")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> delete(SysDict sysDict){
        sysDict = sysDictService.get(sysDict.getId());
        sysDictService.deleteDictListByCode(sysDict.getCode());
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }
}