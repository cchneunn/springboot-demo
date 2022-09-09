package com.cch.demo.global.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.cch.demo.global.mapper.GlobalMapper;
import com.cch.demo.global.page.PageResponse;
import com.cch.demo.global.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public abstract class GlobalServiceImpl<M extends GlobalMapper<T>, T> implements GlobalService<T> {

    @Autowired
    M globalMapper;

    protected static boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    @Override
    public PageResponse<T> page() {
        LambdaQueryWrapper<T> queryWrapper = Wrappers.lambdaQuery();
        Page<T> page = new Page<>(1 , 2);
        IPage<T> result = globalMapper.selectPage(page, queryWrapper);
        return new PageResponse<>(result.getRecords(), result.getTotal(), result.getSize(), result.getCurrent());
    }

    @Override
    public T get(String id) {
        return globalMapper.selectById(id);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public T save(T entity) {
        boolean success = retBool(globalMapper.insert(entity));
        if(success) {
            return entity;
        }
        throw new RuntimeException("保存失败");
    }

}
