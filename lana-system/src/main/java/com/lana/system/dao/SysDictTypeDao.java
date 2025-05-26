package com.lana.system.dao;

import com.lana.base.mybatis.dao.BaseDao;
import com.lana.system.entity.SysDictTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDictTypeDao extends BaseDao<SysDictTypeEntity> {

    List<SysDictTypeEntity> getList();
}
