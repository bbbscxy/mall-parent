package com.vim.common.freemarker;

import com.vim.common.utils.UserUtils;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @作者 Administrator
 * @时间 2019-07-26 10:29
 * @版本 1.0
 * @说明 freemarker 函数: 用户信息
 */
@Component
public class UserInfoTemplate implements TemplateMethodModelEx {

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return UserUtils.currentUser();
    }

}
