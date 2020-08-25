package com.vim.modules.sys.controller;

import com.vim.common.utils.EhcacheUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

/**
 * @作者 Administrator
 * @时间 2019-08-13 18:43
 * @版本 1.0
 */
@Controller
@RequestMapping(value = "/sysCache")
public class SysCacheController {

    /**
     * 列表页面
     */
    @RequiresPermissions(value = "sys:cache:list")
    @RequestMapping(value = {"", "/list"})
    public String list(){
        return "sys/cache/list";
    }

    /**
     * 获取缓存集合
     */
    @RequiresPermissions(value = "sys:cache:list")
    @RequestMapping(value = "/cacheNameList")
    @ResponseBody
    public ResponseEntity<Set<String>> cacheNameList(){
        return new ResponseEntity<>(EhcacheUtils.cacheNameSet(), HttpStatus.OK);
    }

    /**
     * 获取缓存key
     */
    @RequiresPermissions(value = "sys:cache:list")
    @RequestMapping(value = "/cacheKeyList")
    @ResponseBody
    public ResponseEntity<List<String>> cacheKeyList(@RequestParam(value = "cacheName") String cacheName){
        return new ResponseEntity<>(EhcacheUtils.cacheKeySet(cacheName), HttpStatus.OK);
    }

    /**
     * 获取缓存value
     */
    @RequiresPermissions(value = "sys:cache:list")
    @RequestMapping(value = "/cacheValue")
    @ResponseBody
    public ResponseEntity<Object> cacheValue(@RequestParam(value = "cacheName") String cacheName, @RequestParam(value = "cacheKey") String cacheKey){
        return new ResponseEntity<>(EhcacheUtils.get(cacheName, cacheKey, Object.class), HttpStatus.OK);
    }
}
