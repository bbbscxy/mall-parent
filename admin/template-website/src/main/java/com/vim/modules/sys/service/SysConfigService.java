package com.vim.modules.sys.service;

import com.vim.common.base.CrudService;
import com.vim.common.constants.SysCacheConstants;
import com.vim.modules.sys.dao.SysConfigDao;
import com.vim.modules.sys.model.SysConfig;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @作者 Administrator
* @时间 2019-07-23 16:16:54
* @版本 1.0
* @说明 系统配置业务层
*/
@Service
@CacheConfig(cacheNames = SysCacheConstants.EHCACHE_NAME.CONFIG)
public class SysConfigService extends CrudService<SysConfigDao, SysConfig> {

    /**
     * 查询配置信息
     * @param code 配置标识
     */
    @Cacheable(key = "'config_'.concat(#code)")
    public SysConfig findConfigByCode(String code) {
        return dao.findByCode(code);
    }

    @CacheEvict(key = "'config_'.concat(#sysConfig.code)")
    @Override
    public void update(SysConfig sysConfig) {
        super.update(sysConfig);
    }

    /**
     * 根据code删除配置
     * @param code
     */
    @CacheEvict(key = "'config_'.concat(#code)")
    public void deleteByCode(String code) {
        dao.deleteByCode(code);
    }

    /**
     *  查重
     */
    public boolean check(String code) {
        SysConfig findConfig = new SysConfig();
        findConfig.setCode(code);
        List<SysConfig> sysConfigList = findList(findConfig);
        return sysConfigList.size() > 0 ? true : false;
    }
}