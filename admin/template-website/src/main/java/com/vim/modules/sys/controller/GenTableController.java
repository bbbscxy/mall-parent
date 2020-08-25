package com.vim.modules.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vim.common.base.BaseController;
import com.vim.modules.sys.model.GenTable;
import com.vim.modules.sys.model.GenTableColumn;
import com.vim.modules.sys.service.GenTableColumnService;
import com.vim.modules.sys.service.GenTableService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
* @作者 Administrator
* @时间 2019-07-25 14:42:10
* @版本 1.0
* @说明
*/
@Controller
@RequestMapping(value = "/genTable")
public class GenTableController extends BaseController{

    @Autowired
    private GenTableService genTableService;
    @Autowired
    private GenTableColumnService genTableColumnService;

    /**
     * 初始化所有表
     */
    @RequiresPermissions(value = "sys:gen:render")
    @PostMapping(value = "/initTableList")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> initTableList(){
        genTableService.initTableList();
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    /**
     * 预览
     */
    @RequiresPermissions(value = "sys:gen:render")
    @RequestMapping(value = "/preRender")
    public String preRender(@RequestParam(value = "id") String id, Model model){
        model.addAttribute("data", genTableService.preRender(id));
        return "sys/gen/preList";
    }

    /**
     * 生成所有代码
     */
    @RequiresPermissions(value = "sys:gen:render")
    @PostMapping(value = "/render")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> render(){
        genTableService.renderAll();
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    /**
    * 列表页面
    */
    @RequiresPermissions(value = "sys:gen:list")
    @RequestMapping(value = {"", "/list"})
    public String list(){
        return "sys/gen/list";
    }

    /**
    * 列表数据
    */
    @RequiresPermissions(value = "sys:gen:list")
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public PageInfo<GenTable> dataList(GenTable genTable){
        PageHelper.startPage(genTable.getPageNum(), genTable.getPageSize());
        return genTableService.findPageList(genTable);
    }

    /**
     * 配置列表页面
     */
    @RequiresPermissions(value = "sys:gen:config")
    @RequestMapping(value = "/configList")
    public String configList(GenTable genTable, Model model) {
        model.addAttribute("tableId", genTable.getId());
        return "sys/gen/configList";
    }

    /**
     * 配置字段列表数据
     */
    @RequiresPermissions(value = "sys:gen:config")
    @RequestMapping(value = "/configDataList")
    @ResponseBody
    public List<GenTableColumn> configDataList(GenTableColumn genTableColumn){
        return genTableColumnService.findList(genTableColumn);
    }

    /**
     * 配置
     */
    @RequiresPermissions(value = "sys:gen:config")
    @PostMapping(value = "/config")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> config(GenTableColumn column){
        genTableColumnService.updateFieldPolicy(column);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }
}