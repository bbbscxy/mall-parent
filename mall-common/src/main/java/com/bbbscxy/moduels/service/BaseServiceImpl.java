package com.bbbscxy.moduels.service;

import com.bbbscxy.common.service.BaseService;
import com.bbbscxy.moduels.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @作者 Administrator
 * @时间 2020-08-20 15:18
 * @版本 1.0
 * @说明 业务实现层基类
 */
public class BaseServiceImpl<M extends BaseMapper, T> implements BaseService<T> {

    @Autowired
    private M mapper;

    /**
     * 保存记录
     * @param entity 实体
     * @return 修改条数
     */
    public int save(T entity){
        return mapper.save(entity);
    }

    /**
     * 删除记录
     * @param id 记录主键ID
     * @return 修改条数
     */
    public int delete(Long id){
        return mapper.delete(id);
    }

    /**
     * 修改记录
     * @param entity 实体
     * @return 修改条数
     */
    public int update(T entity){
        return mapper.update(entity);
    }

    /**
     * 查询记录
     * @param id 查询记录
     * @return 返回实体
     */
    public T get(Long id){
        return (T) mapper.get(id);
    }

    /**
     * 查询多条记录
     * @param entity 查询条件
     * @return 返回多条记录
     */
    public List<T> findList(T entity){
        return mapper.findList(entity);
    }
}
