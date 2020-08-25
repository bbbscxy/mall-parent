package com.vim.modules.sys.service;

import com.vim.common.base.CrudService;
import com.vim.common.constants.SysCacheConstants;
import com.vim.common.constants.SysDictConstants;
import com.vim.modules.sys.dao.SysDictDao;
import com.vim.modules.sys.model.SysDict;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @作者 Administrator
* @时间 2019-07-23 16:30:56
* @版本 1.0
* @说明 系统字典业务层
*/
@Service
@CacheConfig(cacheNames = SysCacheConstants.EHCACHE_NAME.DICT)
public class SysDictService extends CrudService<SysDictDao, SysDict> {

    /**
     * 编辑字典
     * @param sysDict
     */
    @Transactional
    @CacheEvict(key = "'dict_'.concat(#sysDict.code)")
    @Override
    public void saveOrUpdate(SysDict sysDict) {
        String[] name = sysDict.getName().split(",");
        String[] value = sysDict.getValue().split(",");
        String[] srot = sysDict.getSort().split(",");
        //更新父级字典
        sysDict.setName("");
        sysDict.setValue("");
        sysDict.setSort("");
        sysDict.setIsParent(SysDictConstants.DICT_IS_PARENT.YES.getValue());
        if(StringUtils.isNoneBlank(sysDict.getId())){
            super.update(sysDict);
        }else{
            super.save(sysDict);
        }
        //删除字典列表
        dao.deleteByCode(sysDict.getCode());
        //添加字典列表
        for(int i=0; i<name.length; i++){
            SysDict dict = new SysDict();
            dict.setLabel(sysDict.getLabel());
            dict.setCode(sysDict.getCode());
            dict.setName(name[i]);
            dict.setValue(value[i]);
            dict.setSort(srot[i]);
            dict.setIsParent(SysDictConstants.DICT_IS_PARENT.NO.getValue());
            super.save(dict);
        }
    }

    /**
     * 根据code删除字典
     * @param code
     */
    @CacheEvict(key = "'dict_'.concat(#code)")
    public void deleteDictListByCode(String code) {
        dao.deleteAllByCode(code);
    }

    /**
     * 根据code查找字典
     * @param code
     */
    @Cacheable(key = "'dict_'.concat(#code)")
    public List<SysDict> getDictListByCode(String code){
        return dao.getDictListByCode(code);
    }

    /**
     * 查重
     * @param code
     * @return
     */
    public boolean check(String code) {
        SysDict findDict = new SysDict();
        findDict.setCode(code);
        List<SysDict> dictList =  findList(findDict);
        return dictList.size() > 0 ? true : false;
    }
}