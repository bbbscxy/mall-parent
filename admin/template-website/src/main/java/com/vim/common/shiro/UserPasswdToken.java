package com.vim.common.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @作者 Administrator
 * @时间 2019-07-18 19:57
 * @版本 1.0
 * @说明 用户验证主体
 */
public class UserPasswdToken extends UsernamePasswordToken {

    private boolean rememberMe;

    public UserPasswdToken(String username, String password) {
        super(username, password);
    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }

    @Override
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}