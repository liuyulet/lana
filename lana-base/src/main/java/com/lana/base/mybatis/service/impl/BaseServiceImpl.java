package com.lana.base.mybatis.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lana.base.syshandle.page.LanaPageParams;
import com.lana.base.mybatis.service.BaseService;

/**
 * @auther liuyulet
 * @date 2024/3/16 14:22
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
    /**
     * 获取分页对象，方便Ipml层使用
     *
     * @param query 分页参数
     */
    protected IPage<T> getPage(LanaPageParams query) {
        Page<T> page = new Page<>(query.getPage(), query.getPageSize());
        return page;
    }

}
