package com.vim.common.utils;

import net.sf.ehcache.Ehcache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

/**
 * @作者 Administrator
 * @时间 2019-07-26 8:57
 * @版本 1.0
 * @说明 Ehcache 缓存操作类
 */
@Component
public class EhcacheUtils {

    private static EhcacheUtils ehcacheUtils;
    @PostConstruct
    public void init() {
        ehcacheUtils = this;
    }

    @Autowired
    private EhCacheCacheManager cacheManager;

    /**
     * 添加缓存
     */
    public static void put(String cacheName, String key, Object value){
        ehcacheUtils.cacheManager.getCache(cacheName).put(key, value);
    }

    /**
     * 查询缓存对象
     */
    public static <T> T get(String cacheName, String key, Class<T> t){
        return ehcacheUtils.cacheManager.getCache(cacheName).get(key, t);
    }

    /**
     * 查询缓存列表
     */
    public static <T> List<T> getList(String cacheName, String key){
        return ehcacheUtils.cacheManager.getCache(cacheName).get(key, List.class);
    }

    /**
     * 删除缓存
     */
    public static void delete(String cacheName, String key){
        ehcacheUtils.cacheManager.getCache(cacheName).put(key, null);
    }

    /**
     * 清空缓存
     * @param cacheName
     */
    public static void clear(String cacheName){
        ehcacheUtils.cacheManager.getCache(cacheName).clear();
    }

    /**
     * 所有的缓存集合
     */
    public static Set<String> cacheNameSet(){
        return (Set<String>) ehcacheUtils.cacheManager.getCacheNames();
    }

    /**
     * 获取某个缓存集合中的key集合
     * @param cacheName
     * @return
     */
    public static List<String> cacheKeySet(String cacheName){
        return ehcacheUtils.cacheManager.getCacheManager().getCache(cacheName).getKeys();
    }
}