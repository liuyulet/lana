package com.lana.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lana.modules.system.pojo.entity.SysProjectEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * (SysProject)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-06 11:23:37
 */
@Mapper
public interface SysProjectDao extends BaseMapper<SysProjectEntity> {


    SysProjectEntity getProjects(String demanProject);
}

