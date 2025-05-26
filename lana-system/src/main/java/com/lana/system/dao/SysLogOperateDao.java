package com.lana.system.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.mybatis.dao.BaseDao;
import com.lana.base.operatelog.entity.OptLogEntity;
import com.lana.system.entity.SysLogOperateEntity;
import com.lana.system.entity.vo.query.SysLogOperateQuery;
import com.lana.system.entity.vo.query.SysLogSysOperateQuery;
import com.lana.system.entity.vo.result.SysLogOperateResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysLogOperateDao extends BaseDao<SysLogOperateEntity> {

    // 目前启用该版本的日志
    IPage<SysLogOperateResult> getLists(@Param("page")IPage<SysLogOperateEntity> page, @Param("query")SysLogOperateQuery model);

    @DS("tdengine")
    void insertTd(@Param("sql")String sql);

    @DS("tdengine")
    IPage<SysLogOperateResult> getListsTd(@Param("page")IPage<SysLogOperateEntity> page,@Param("query") SysLogOperateQuery query);
    @DS("tdengine")
    IPage<SysLogOperateResult> getSysListsTd(@Param("page")IPage<SysLogOperateEntity> page,@Param("query") SysLogSysOperateQuery query);
    @DS("tdengine")
    IPage<SysLogOperateResult> getSysLogListsTd(IPage<SysLogOperateEntity> page, SysLogSysOperateQuery query);
    @DS("tdengine")
    IPage<SysLogOperateResult> getdeviceLogListsTd(IPage<SysLogOperateEntity> page, SysLogSysOperateQuery query);
    @DS("tdengine")
    IPage<SysLogOperateResult> getCommunLogListsTd(IPage<SysLogOperateEntity> page, SysLogSysOperateQuery query);
}
