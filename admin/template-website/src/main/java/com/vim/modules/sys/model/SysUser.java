package com.vim.modules.sys.model;

import com.vim.common.annotation.ExcelField;
import com.vim.common.base.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
* @作者 Administrator
* @时间 2019-07-19 12:21:31
* @版本 1.0
* @说明 系统用户
*/
public class SysUser extends DataEntity {

    public interface updatePassword{}

    /**
    * 登录名
    */
    @ExcelField(title = "账号", sort = 1)
    @NotNull(message = "账号不能为空")
    @Length(min = 2, max = 40, message = "账号长度要在2-40之间")
    private String loginName;
    /**
    * 密码
    */
    @Length(groups = updatePassword.class, min= 6, max = 20, message = "密码长度要在6-20之间")
    private String password;
    /**
    * 姓名
    */
    @ExcelField(title = "姓名", sort = 2)
    @NotNull(message = "姓名不能为空")
    @Length(min = 2, max = 40, message = "姓名长度要在2-40之间")
    private String name;
    /**
    * 电话
    */
    private String phone;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 用户类型(1 管理员  2 普通用户)
    */
    private String userType;
    /**
     * 部门编号
     */
    private String deptId;
    /**
    * 用户头像
    */
    @Length(max = 200, message = "用户头像地址最大长度为200")
    private String photo;
    /**
    * 最后登陆IP
    */
    private String loginIp;
    /**
    * 最后登陆时间
    */
    private String loginDate;
    /**
    * 是否可登录
    */
    private String loginFlag;
    /**
     * 用户角色
     */
    private SysRole sysRole;
    /**
     * 部门
     */
    private SysDept sysDept;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getPhoto() {
        return photo;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }
    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public SysDept getSysDept() {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }
}