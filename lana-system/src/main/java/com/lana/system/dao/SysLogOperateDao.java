package com.lana.system.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.mybatis.dao.BaseDao;
import com.lana.base.operatelog.entity.OptLogEntity;
import com.lana.system.entity.SysLogOperateEntity;
import com.lana.system.entity.vo.query.SysLogOperateQuery;
import com.lana.system.entity.vo.result.SysLogOperateResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysLogOperateDao extends BaseDao<SysLogOperateEntity> {

    IPage<SysLogOperateResult> getLists(@Param("page")IPage<SysLogOperateEntity> page, @Param("query")SysLogOperateQuery model);

    @DS("tdengine")
    void insertTd(@Param("sql")String sql);

    @DS("tdengine")
    IPage<SysLogOperateResult> getListsTd(@Param("page")IPage<SysLogOperateEntity> page,@Param("query") SysLogOperateQuery query);
}
