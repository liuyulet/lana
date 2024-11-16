package com.lana.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.mybatis.dao.BaseDao;
import com.lana.system.entity.SysDictDataEntity;
import com.lana.system.entity.vo.query.SysDictDataQuery;
import com.lana.system.entity.vo.result.SysDictDataResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDictDataDao extends BaseDao<SysDictDataEntity> {

    IPage<SysDictDataResult> getLists(@Param("page") IPage<SysDictDataEntity> page, @Param("query")SysDictDataQuery model);
}
