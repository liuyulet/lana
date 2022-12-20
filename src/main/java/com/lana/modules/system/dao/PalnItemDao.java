package com.lana.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lana.modules.system.pojo.entity.PalnItemEntity;
import com.lana.modules.system.pojo.entity.SysUserDepartRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (PalnItem)表数据库访问层
 *
 * @author liuyulet
 * @since 2022-11-03 15:16:12
 */
@Mapper
public interface PalnItemDao  extends BaseMapper<PalnItemEntity> {



}

