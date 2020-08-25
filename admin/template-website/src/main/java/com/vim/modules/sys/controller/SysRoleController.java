package com.vim.modules.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vim.common.base.BaseController;
import com.vim.modules.sys.model.SysMenu;
import com.vim.modules.sys.model.SysRole;
import com.vim.modules.sys.model.ZTreeNode;
import com.vim.modules.sys.service.SysMenuService;
import com.vim.modules.sys.service.SysRoleService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @作者 Administrator
 * @时间 2019-07-18 19:41
 * @版本 1.0
 * @说明 系统角色控制层
 */
@Controller
@RequestMapping(value = "/sysRole")
public class SysRoleController extends BaseController{

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 列表页面
     */
    @RequiresPermissions(value = "sys:role:list")
    @RequestMapping(value = {"", "/list"})
    public String list(){
        return "sys/role/list";
    }

    /**
     * 列表数据
     */
    @RequiresPermissions(value = "sys:role:list")
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public PageInfo<SysRole> dataList(SysRole sysRole){
        PageHelper.startPage(sysRole.getPageNum(), sysRole.getPageSize());
        return sysRoleService.findPageList(sysRole);
    }

    /**
     * 角色权限树列表数据
     */
    @RequiresPermissions(value = "sys:role:form")
    @RequestMapping(value = "/zTreeDataList")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> zTreeDataList(@RequestParam(value = "roleId") String roleId){
        List<ZTreeNode> data = new ArrayList<>();
        List<SysMenu> menuList = sysMenuService.findList(new SysMenu());
        List<String> roldMenuIds = sysMenuService.getRoleMenuIds(roleId);
        for(SysMenu menu:menuList){
            ZTreeNode node = new ZTreeNode();
            node.setId(Long.valueOf(menu.getId()));
            node.setIcon("");
            node.setName(menu.getName());
            node.setpId(Long.valueOf(menu.getParentId()));
            node.setOpen(true);
            node.setChecked(roldMenuIds.contains(menu.getId()) ? true : false);
            data.add(node);
        }
        return new ResponseEntity<>(success(data), HttpStatus.OK);
    }

    /**
     * 编辑页面
     */
    @RequiresPermissions(value = "sys:role:form")
    @RequestMapping(value = "/form")
    public String form(SysRole sysRole, Model model){
        model.addAttribute("menuIds", StringUtils.isNoneBlank(sysRole.getId()) ? StringUtils.join(sysMenuService.getRoleMenuList(sysRole.getId()), ",") : "");
        model.addAttribute("sysRole", StringUtils.isNoneBlank(sysRole.getId()) ? sysRoleService.get(sysRole.getId()) : new SysRole());
        return "sys/role/form";
    }

    /**
     * 更新数据
     */
    @RequiresPermissions(value = "sys:role:form")
    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save(@Validated SysRole sysRole, @RequestParam(value = "menuIds") String menuIds){
        sysRoleService.saveOrUpdateRole(sysRole, menuIds);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    /**
     * 删除
     */
    @RequiresPermissions(value = "sys:role:delete")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> delete(SysRole sysRole){
        sysRoleService.delete(sysRole.getId());
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }
}