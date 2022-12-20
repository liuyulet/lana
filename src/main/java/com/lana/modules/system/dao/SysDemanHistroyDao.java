package com.lana.modules.system.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lana.modules.system.pojo.entity.SysDeman;
import com.lana.modules.system.pojo.entity.SysDemanHistroyEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * 需求变更历史表(SysDemanHistroy)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-06 13:55:12
 */
@Mapper
public interface SysDemanHistroyDao extends BaseMapper<SysDemanHistroyEntity> {



}

