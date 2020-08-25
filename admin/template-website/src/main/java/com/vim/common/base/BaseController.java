package com.vim.common.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者 Administrator
 * @时间 2019-07-22 9:29
 * @版本 1.0
 * @说明 控制器基类
 */
public class BaseController {

    public Map<String, Object> success(){
        Map<String, Object> result = new HashMap<>();
        result.put("code", "SUCCESS");
        result.put("msg", "操作成功!");
        return result;
    }

    public Map<String, Object> success(Object data){
        Map<String, Object> result = new HashMap<>();
        result.put("code", "SUCCESS");
        result.put("msg", "操作成功!");
        result.put("data", data);
        return result;
    }

}
