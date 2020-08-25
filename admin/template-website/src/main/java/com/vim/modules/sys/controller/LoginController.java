package com.vim.modules.sys.controller;

import com.vim.common.base.BaseController;
import com.vim.common.queue.LoginLogThread;
import com.vim.common.shiro.UserPasswdToken;
import com.vim.modules.sys.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @作者 Administrator
 * @时间 2019-07-18 19:41
 * @版本 1.0
 * @说明 登录控制层
 */
@Controller
public class LoginController extends BaseController{

    @RequestMapping(value = "/loginPage")
    public String loginPage(){
        return "sys/user/login";
    }

    /**
     * 登录
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login(HttpServletRequest request, SysUser user){
        SecurityUtils.getSubject().login(new UserPasswdToken(user.getLoginName(), user.getPassword()));
        LoginLogThread.record(request);
        return new ResponseEntity(success(), HttpStatus.OK);
    }

    /**
     * 登出
     */
    @PostMapping(value = "/logout")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> logout(){
        SecurityUtils.getSubject().logout();
        return new ResponseEntity(success(), HttpStatus.OK);
    }
}
