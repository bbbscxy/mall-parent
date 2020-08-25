package com.vim.common.freemarker;

import com.vim.common.constants.Global;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @作者 Administrator
 * @时间 2019-07-26 10:29
 * @版本 1.0
 * @说明 freemarker 函数: 字典数据
 */
@Component
public class DictListTemplate implements TemplateMethodModelEx {

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return Global.getDictList(arguments.get(0).toString());
    }

}
