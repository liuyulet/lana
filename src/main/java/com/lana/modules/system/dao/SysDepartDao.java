package com.lana.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lana.modules.system.pojo.entity.SysDepart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 组织机构表(SysDepart)表数据库访问层
 *
 * @author liuyulet
 * @since 2022-09-29 10:02:34
 */
@Mapper
public interface SysDepartDao extends BaseMapper<SysDepart> {

    List<SysDepart> getlist();
}

