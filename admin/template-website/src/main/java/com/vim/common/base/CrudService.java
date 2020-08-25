package com.vim.common.base;

import com.github.pagehelper.PageInfo;
import com.vim.common.constants.SysDictConstants;
import com.vim.common.utils.EncryUtils;
import com.vim.common.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
* @作者 Administrator
* @时间 2019-07-19 12:21:31
* @版本 1.0
* @说明 Service 增删改查基类
*/
public class CrudService<D extends CrudDao<T>, T extends DataEntity> implements BaseService {

    /**
    * 持久层对象
    */
    @Autowired
    protected D dao;

    /**
     * 保存
     */
    public void saveOrUpdate(T entity){
        if(StringUtils.isNotBlank(entity.getId())){
            update(entity);
        }else{
            save(entity);
        }
    }

    /**
    * 保存
    */
    public void save(T entity){
        Date date = new Date();
        entity.setId(EncryUtils.primaryKey());
        entity.setCreateBy(UserUtils.currentUser().getId());
        entity.setCreateDate(date);
        entity.setUpdateBy(UserUtils.currentUser().getId());
        entity.setUpdateDate(date);
        dao.save(entity);
    }

    /**
    * 更新
    */
    public void update(T entity){
        entity.setUpdateBy(UserUtils.currentUser().getId());
        entity.setUpdateDate(new Date());
        dao.update(entity);
    }

    /**
    * 删除
    */
    public void delete(String id){
        dao.delete(id);
    }

    /**
    * 查询单个
    */
    public T get(String id){
        return dao.get(id);
    }

    /**
    * 查询所有
    */
    public List<T> findList(T entity){
        entity.setDelFlag(SysDictConstants.DEL_FLAG.NO.getValue());
        return dao.findList(entity);
    }

    /**
    * 分页查询所有
    */
    public PageInfo<T> findPageList(T entity){
        entity.setDelFlag(SysDictConstants.DEL_FLAG.NO.getValue());
        return new PageInfo<>(dao.findList(entity));
    }

}
