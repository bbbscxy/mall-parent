package com.vim.common.base;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
* @作者 Administrator
* @时间 2019-07-19 12:21:31
* @版本 1.0
* @说明 Dao 增删改查基类
*/
public interface CrudDao<T> extends BaseDao {

    /**
    * 保存
    */
    void save(T entity);

    /**
    * 更新
    */
    void update(T entity);

    /**
    * 删除
    */
    void delete(String id);

    /**
    * 查询单个
    */
    T get(String id);

    /**
    * 查询所有
    */
    List<T> findList(T entity);

    /**
    * 查询所有
    */
    PageInfo<T> findPageList(T entity);

}
