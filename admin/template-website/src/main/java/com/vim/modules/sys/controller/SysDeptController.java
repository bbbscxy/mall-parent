package com.vim.modules.sys.controller;

import com.vim.common.base.BaseController;
import com.vim.modules.sys.model.SysDept;
import com.vim.modules.sys.model.SysDeptTree;
import com.vim.modules.sys.model.ZTreeNode;
import com.vim.modules.sys.service.SysDeptService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.vim.common.constants.Global.TREE_ROOT_ID;

/**
* @作者 Administrator
* @时间 2019-08-11 10:24:36
* @版本 1.0
* @说明
*/
@Controller
@RequestMapping(value = "/sysDept")
public class SysDeptController extends BaseController{

    @Autowired
    private SysDeptService sysDeptService;

    /**
    * 列表页面
    */
    @RequiresPermissions(value = "sys:dept:list")
    @RequestMapping(value = {"", "/list"})
    public String list(){
        return "sys/dept/list";
    }

    /**
     * 选择页面
     */
    @RequestMapping(value = "/select")
    public String select(@RequestParam(value = "type") String type, Model model){
        model.addAttribute("type", type);
        return "sys/dept/select";
    }

    /**
     * 组织机构树列表数据
     */
    @RequestMapping(value = "/zTreeDataList")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> zTreeDataList(){
        List<ZTreeNode> data = new ArrayList<>();
        List<SysDept> deptList = sysDeptService.findList(new SysDept());
        for(SysDept dept:deptList){
            ZTreeNode node = new ZTreeNode();
            node.setId(Long.valueOf(dept.getId()));
            node.setIcon("");
            node.setName(dept.getDeptName());
            node.setpId(Long.valueOf(dept.getParentId()));
            node.setpIds(dept.getParentIds());
            node.setOpen(true);
            data.add(node);
        }
        return new ResponseEntity<>(success(data), HttpStatus.OK);
    }

    /**
    * 列表数据
    */
    @RequiresPermissions(value = "sys:dept:list")
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> dataList(@RequestParam(value = "nodeid", required = false) String parentId,
                                      @RequestParam(value = "deptName", defaultValue = "") String deptName){
        List<SysDeptTree> data = new ArrayList<>();
        //1.查询子节点列表
        SysDept findDept = new SysDept();
        findDept.setDeptName(StringUtils.isBlank(parentId) ? deptName : "");
        findDept.setParentId(StringUtils.isBlank(parentId) ? (StringUtils.isBlank(deptName) ? TREE_ROOT_ID : "") : parentId);
        List<SysDept> deptList = sysDeptService.findList(findDept);
        for(SysDept dept:deptList){
            //2.构建节点
            SysDeptTree node = new SysDeptTree();
            node.setId(Long.valueOf(dept.getId()));
            node.setParentId(Long.valueOf(dept.getParentId()));
            node.setLevel(Long.valueOf(dept.getParentIds().split(",").length-1));
            node.setName(dept.getDeptName());
            node.setLeaderName(dept.getLeaderName());
            node.setCreateDate(dept.getCreateDate());
            node.setExpanded(false);
            //3.查询是否还有子节点
            findDept = new SysDept();
            findDept.setParentId(dept.getId());
            node.setLeaf(sysDeptService.findList(findDept).size() > 0 ? false : true);
            data.add(node);
        }
        return new ResponseEntity<>(success(data), HttpStatus.OK);
    }

    /**
    * 编辑页面
    */
    @RequiresPermissions(value = "sys:dept:form")
    @RequestMapping(value = "/form")
    public String form(SysDept sysDept, Model model){
        String parentDeptName = "";
        if(StringUtils.isNoneBlank(sysDept.getId())){
            SysDept dept = sysDeptService.get(sysDept.getId());
            parentDeptName = TREE_ROOT_ID.equals(dept.getParentId()) ? "" : sysDeptService.get(dept.getParentId()).getDeptName();
        }
        SysDept dept = new SysDept();
        dept.setParentId(TREE_ROOT_ID);
        dept.setParentIds(TREE_ROOT_ID);
        model.addAttribute("sysDept", StringUtils.isNoneBlank(sysDept.getId()) ? sysDeptService.get(sysDept.getId()) : dept);
        model.addAttribute("parentDeptName", parentDeptName);
        return "sys/dept/form";
    }

    /**
     * 子部门新增页面
     */
    @RequiresPermissions(value = "sys:dept:form")
    @RequestMapping(value = "/addChildDept")
    public String addChildMenu(SysDept parent, Model model){
        SysDept children = new SysDept();

        parent = sysDeptService.get(parent.getId());
        children.setParentId(parent.getId());
        children.setParentIds(parent.getId()+","+parent.getParentIds());

        model.addAttribute("sysDept", children);
        model.addAttribute("parentDeptName", parent.getDeptName());
        return "sys/dept/form";
    }

    /**
    * 更新数据
    */
    @RequiresPermissions(value = "sys:dept:form")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save(@Validated SysDept sysDept){
        sysDeptService.saveOrUpdate(sysDept);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    /**
    * 删除
    */
    @RequiresPermissions(value = "sys:dept:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> delete(SysDept sysDept){
        sysDeptService.delete(sysDept.getId());
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }
}