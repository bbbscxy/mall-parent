package com.bbbscxy.common.service;

import java.util.List;

/**
 * @作者 Administrator
 * @时间 2020-08-20 15:15
 * @版本 1.0
 * @说明 业务层基类
 */
public interface BaseService<T> {

    /**
     * 保存记录
     * @param entity 实体
     * @return 修改条数
     */
    int save(T entity);

    /**
     * 删除记录
     * @param id 记录主键ID
     * @return 修改条数
     */
    int delete(Long id);

    /**
     * 修改记录
     * @param entity 实体
     * @return 修改条数
     */
    int update(T entity);

    /**
     * 查询记录
     * @param id 查询记录
     * @return 返回实体
     */
    T get(Long id);

    /**
     * 查询多条记录
     * @param entity 查询条件
     * @return 返回多条记录
     */
    List<T> findList(T entity);
}
