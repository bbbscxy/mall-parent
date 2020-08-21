package com.bbbscxy.common.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @作者 Administrator
 * @时间 2020-08-21 14:36
 * @版本 1.0
 * @说明
 */
public class BaseServiceImpl<M extends BaseMapper, T> implements BaseService<T>{

    @Autowired
    private M mapper;

    /**
     * 保存记录
     * @param entity 实体
     * @return 修改条数
     */
    public int save(T entity){
        return mapper.insert(entity);
    }

    /**
     * 删除记录
     * @param id 记录主键ID
     * @return 修改条数
     */
    public int delete(Long id){
        return mapper.deleteById(id);
    }

    /**
     * 修改记录
     * @param entity 实体
     * @return 修改条数
     */
    public int update(T entity){
        return mapper.updateById(entity);
    }

    /**
     * 查询记录
     * @param id 查询记录
     * @return 返回实体
     */
    public T get(Long id){
        return (T) mapper.selectById(id);
    }

    /**
     * 查询多条记录
     * @param entity 查询条件
     * @return 返回多条记录
     */
    public List<T> findList(T entity){
        QueryWrapper<T> query = new QueryWrapper<T>(entity);
        return mapper.selectList(query);
    }

    public IPage<T> findListPage(Page<T> page, T entity) {
        QueryWrapper<T> query = new QueryWrapper<T>(entity);
        return mapper.selectPage(page, query);
    }
}
