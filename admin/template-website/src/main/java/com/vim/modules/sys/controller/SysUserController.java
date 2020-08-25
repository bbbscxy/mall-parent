package com.vim.modules.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vim.common.base.BaseController;
import com.vim.common.exception.BusinessException;
import com.vim.common.utils.EncryUtils;
import com.vim.common.utils.ExcelUtils;
import com.vim.common.utils.UserUtils;
import com.vim.modules.sys.model.SysMenu;
import com.vim.modules.sys.model.SysRole;
import com.vim.modules.sys.model.SysUser;
import com.vim.modules.sys.model.ZTreeNode;
import com.vim.modules.sys.service.SysMenuService;
import com.vim.modules.sys.service.SysRoleService;
import com.vim.modules.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.vim.common.constants.Global.USER_ADMIN_ID;

/**
 * @作者 Administrator
 * @时间 2019-07-18 19:41
 * @版本 1.0
 * @说明 系统用户控制层
 */
@Controller
@RequestMapping(value = "/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 列表页面
     */
    @RequiresPermissions(value = "sys:user:list")
    @RequestMapping(value = "menuList")
    public String menuList(@RequestParam(value = "roleId") String roleId, Model model){
        model.addAttribute("roleId", roleId);
        return "sys/user/menuList";
    }

    /**
     * 组织机构树列表数据
     */
    @RequestMapping(value = "/menuZTreeDataList")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> zTreeDataList(@RequestParam(value = "roleId") String roleId){
        List<ZTreeNode> data = new ArrayList<>();
        List<SysMenu> menuList = sysMenuService.getRoleMenuList(roleId);
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
     * 列表页面
     */
    @RequiresPermissions(value = "sys:user:list")
    @RequestMapping(value = {"", "/list"})
    public String list(){
        return "sys/user/list";
    }

    /**
     * 列表数据
     */
    @RequiresPermissions(value = "sys:user:list")
    @RequestMapping(value = "/dataList")
    @ResponseBody
    public PageInfo<SysUser> dataList(SysUser sysUser){
        PageHelper.startPage(sysUser.getPageNum(), sysUser.getPageSize());
        return sysUserService.findPageList(sysUser);
    }

    /**
     * 所有角色
     */
    @RequiresPermissions(value = "sys:user:form")
    @RequestMapping(value = "/roleList")
    @ResponseBody
    public List<SysRole> roleList(){
        return sysRoleService.findList(new SysRole());
    }

    /**
     * 编辑页面
     */
    @RequiresPermissions(value = "sys:user:form")
    @RequestMapping(value = "/form")
    public String form(SysUser sysUser, Model model){
        model.addAttribute("roleIds", StringUtils.isNoneBlank(sysUser.getId()) ? StringUtils.join(sysRoleService.findRoleIdsByUserId(sysUser.getId()), ",") : "");
        model.addAttribute("sysUser", StringUtils.isNoneBlank(sysUser.getId()) ? sysUserService.get(sysUser.getId()) : new SysUser());
        return "sys/user/form";
    }

    /**
     * 更新数据
     */
    @RequiresPermissions(value = "sys:user:form")
    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save(@Validated SysUser sysUser, @RequestParam(value = "roleIds") String roleIds) throws Exception{
        if(StringUtils.isBlank(sysUser.getId()) && null != sysUserService.getSysUserByLoginName(sysUser.getLoginName())){
            throw new BusinessException("账号已存在!");
        }
        sysUserService.saveOrUpdateUser(sysUser, roleIds);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    /**
     * 删除
     */
    @RequiresPermissions(value = "sys:user:delete")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> delete(SysUser sysUser) throws Exception{
        if(USER_ADMIN_ID.equals(sysUser.getId())){
            throw new BusinessException("管理员不允许删除!");
        }
        sysUserService.delete(sysUser.getId());
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    /**
     * 修改密码页面
     */
    @RequestMapping(value = "/updatePasswordForm")
    public String updatePasswordForm(){
        return "sys/user/updatePassword";
    }

    /**
     * 更新密码
     */
    @PostMapping(value = "/updatePassword")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updatePassword(String newPassword, String oldPassword) throws Exception{
        SysUser user = UserUtils.currentUser();
        SysUser updateUser = new SysUser();
        if(null != user && user.getPassword().equals(EncryUtils.encryPassword(user.getLoginName(),oldPassword))){
            updateUser.setPassword(EncryUtils.encryPassword(user.getLoginName(), newPassword));
            updateUser.setId(user.getId());
            sysUserService.update(updateUser);
            SecurityUtils.getSubject().logout();
        }else{
            throw new BusinessException("密码错误");
        }
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    /**
     * 导出
     */
    @RequiresPermissions(value = "sys:user:export")
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(SysUser sysUser, HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode("export201907150905.xls"));
        List<SysUser> dataList = sysUserService.findList(sysUser);
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.exportExcel("用户列表", dataList, SysUser.class, response.getOutputStream());
    }

    /**
     * 修改头像页面
     */
    @RequestMapping(value = "/updateAvatarForm")
    public String updateAvatarForm(){
        return "sys/user/updateAvatar";
    }
}