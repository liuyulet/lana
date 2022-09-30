package com.lana.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lana.modules.system.pojo.entity.SysDeman;
import com.lana.modules.system.pojo.entity.SysDepart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (SysDeman)表数据库访问层
 *
 * @author liuyulet
 * @since 2022-09-30 11:25:38
 */
@Mapper
public interface SysDemanDao extends BaseMapper<SysDeman> {


}

