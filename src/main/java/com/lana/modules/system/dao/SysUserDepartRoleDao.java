package com.lana.modules.system.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lana.modules.system.pojo.entity.SysUserDepartRoleEntity;
import com.lana.modules.system.pojo.vo.SysUserDepartRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * (SysUserDepartRole)表数据库访问层
 *
 * @author liuyulet
 * @since 2022-10-04 18:21:58
 */
@Mapper
public interface SysUserDepartRoleDao extends BaseMapper<SysUserDepartRoleEntity> {


    List<Map<String, Object>> getstepPage(@Param("sss") List<Long> sss);

    IPage<SysUserDepartRoleVO> getUserData(@Param("page") Page<HashMap<String, Object>> page);
}

