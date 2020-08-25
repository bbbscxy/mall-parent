package com.vim.modules.sys.controller;

import com.vim.common.base.BaseController;
import com.vim.common.constants.SysDictConstants;
import com.vim.modules.sys.model.SysMenu;
import com.vim.modules.sys.model.SysMenuTree;
import com.vim.modules.sys.model.ZTreeNode;
import com.vim.modules.sys.service.SysMenuService;
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

import static com.vim.common.constants.Global.TREE_ROOT_ID;

/**
 * @作者 Administrator
 * @时间 2019-07-18 19:41
 * @版本 1.0
 * @说明 系统菜单控制层
 */
@Controller
@RequestMapping(value = "/sysMenu")
public class SysMenuController extends BaseController{

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 列表页面
     */
    @RequiresPermissions(value = "sys:menu:list")
    @RequestMapping(value = {"", "/list"})
    public String list(Model model){
        return "sys/menu/list";
    }

    /**
     * 选择页面
     */
    @RequestMapping(value = "/select")
    public String select(){
        return "sys/menu/select";
    }

    /**
     * 组织机构树列表数据
     */
    @RequestMapping(value = "/zTreeDataList")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> zTreeDataList(){
        List<ZTreeNode> data = new ArrayList<>();
        //1.所有的组织机构
        SysMenu findMenu = new SysMenu();
        findMenu.setIsShow(SysDictConstants.MENU_IS_SHOW.YES.getValue());
        List<SysMenu> menuList = sysMenuService.findList(findMenu);
        for(SysMenu menu:menuList){
            ZTreeNode node = new ZTreeNode();
            node.setId(Long.valueOf(menu.getId()));
            node.setIcon("");
            node.setName(menu.getName());
            node.setpId(Long.valueOf(menu.getParentId()));
            node.setpIds(menu.getParentIds());
            node.setOpen(true);
            data.add(node);
        }
        return new ResponseEntity<>(success(data), HttpStatus.OK);
    }

    /**
     * 列表数据
     */
    @RequiresPermissions(value = "sys:menu:list")
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> dataList(@RequestParam(value = "nodeid", required = false) String parentId,
                                        @RequestParam(value = "menuName", defaultValue = "") String menuName){
        List<SysMenuTree> data = new ArrayList<>();
        //1.查询子节点列表
        SysMenu findMenu = new SysMenu();
        findMenu.setName(StringUtils.isBlank(parentId) ? menuName : "");
        findMenu.setParentId(StringUtils.isBlank(parentId) ? (StringUtils.isBlank(menuName) ? TREE_ROOT_ID : "") : parentId);
        List<SysMenu> menuList = sysMenuService.findList(findMenu);
        for(SysMenu menu:menuList){
            //2.构建节点
            SysMenuTree node = new SysMenuTree();
            node.setId(Long.valueOf(menu.getId()));
            node.setParentId(Long.valueOf(menu.getParentId()));
            node.setLevel(Long.valueOf(menu.getParentIds().split(",").length-1));
            node.setName(menu.getName());
            node.setHref(menu.getHref());
            node.setSort(menu.getSort());
            node.setPermission(menu.getPermission());
            node.setExpanded(false);
            //3.查询是否还有子节点
            findMenu = new SysMenu();
            findMenu.setParentId(menu.getId());
            node.setLeaf(sysMenuService.findList(findMenu).size() > 0 ? false : true);
            data.add(node);
        }
        return new ResponseEntity<>(success(data), HttpStatus.OK);
    }

    /**
     * 编辑页面
     */
    @RequiresPermissions(value = "sys:menu:form")
    @RequestMapping(value = "/form")
    public String form(SysMenu sysMenu, Model model, @RequestParam(value = "type") String type){
        String parentMenuName = "顶层菜单";
        if(StringUtils.isNoneBlank(sysMenu.getId())){
            SysMenu menu = sysMenuService.get(sysMenu.getId());
            parentMenuName = TREE_ROOT_ID.equals(menu.getParentId()) ? "顶层菜单" : sysMenuService.get(menu.getParentId()).getName();
        }
        SysMenu menu = new SysMenu();
        menu.setParentId("0");
        menu.setParentIds("0");
        model.addAttribute("sysMenu", StringUtils.isNoneBlank(sysMenu.getId()) ? sysMenuService.get(sysMenu.getId()) : menu);
        model.addAttribute("parentMenuName", parentMenuName);
        model.addAttribute("type", type);
        return "sys/menu/form";
    }

    /**
     * 子菜单新增页面
     */
    @RequiresPermissions(value = "sys:menu:form")
    @RequestMapping(value = "/addChildMenu")
    public String addChildMenu(SysMenu sysMenu, Model model, @RequestParam(value = "type") String type){
        SysMenu parent = sysMenuService.get(sysMenu.getParentId());
        if(parent.getParentIds().split(",").length == 3){
            type = "button";
        }
        SysMenu menu = new SysMenu();
        menu.setParentId(parent.getId());
        menu.setParentIds(parent.getId()+","+parent.getParentIds());

        model.addAttribute("sysMenu", menu);
        model.addAttribute("parentMenuName", parent.getName());
        model.addAttribute("type", type);
        return "sys/menu/form";
    }

    /**
     * 更新数据
     */
    @RequiresPermissions(value = "sys:menu:form")
    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save(@Validated SysMenu sysMenu){
        sysMenuService.saveOrUpdate(sysMenu);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    /**
     * 删除
     */
    @RequiresPermissions(value = "sys:menu:delete")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> delete(SysMenu sysMenu){
        sysMenuService.delete(sysMenu.getId());
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }
}