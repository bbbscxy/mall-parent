package com.vim.modules.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vim.common.base.BaseController;
import com.vim.modules.sys.model.GenTableColumn;
import com.vim.modules.sys.service.GenTableColumnService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
* @作者 Administrator
* @时间 2019-07-25 14:42:10
* @版本 1.0
* @说明
*/
@Controller
@RequestMapping(value = "/genTableColumn")
public class GenTableColumnController extends BaseController{

    @Autowired
    private GenTableColumnService genTableColumnService;

    /**
    * 列表页面
    */
    @RequiresPermissions(value = "sys:genTableColumn:list")
    @RequestMapping(value = {"", "/list"})
    public String list(){
        return "sys/genTableColumn/list";
    }

    /**
    * 列表数据
    */
    @RequiresPermissions(value = "sys:genTableColumn:list")
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public PageInfo<GenTableColumn> dataList(GenTableColumn genTableColumn){
        PageHelper.startPage(genTableColumn.getPageNum(), genTableColumn.getPageSize());
        return genTableColumnService.findPageList(genTableColumn);
    }

    /**
    * 编辑页面
    */
    @RequiresPermissions(value = "sys:genTableColumn:form")
    @RequestMapping(value = "/form")
    public String form(GenTableColumn genTableColumn, Model model){
        model.addAttribute("genTableColumn", StringUtils.isNoneBlank(genTableColumn.getId()) ? genTableColumnService.get(genTableColumn.getId()) : new GenTableColumn());
        return "sys/genTableColumn/form";
    }

    /**
    * 更新数据
    */
    @RequiresPermissions(value = "sys:genTableColumn:form")
    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save(GenTableColumn genTableColumn){
        if(StringUtils.isNoneBlank(genTableColumn.getId())){
            genTableColumnService.update(genTableColumn);
        }else{
            genTableColumnService.save(genTableColumn);
        }
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

}