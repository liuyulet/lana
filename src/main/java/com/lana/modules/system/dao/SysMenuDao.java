package com.lana.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lana.modules.system.pojo.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单管理(SysMenu)表数据库访问层
 * @author liuyulet
 * @since 2022-10-05 15:32:24
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    List<SysMenuEntity> getNavData(String userid);

    List<HashMap<String,Object>> getMenurole(Long role);

    void userUpdateForDepart(@Param("roleId")Integer roleId, @Param("userUpdateList")List<Integer> userUpdateList);

    void deleteRolMen(Integer roleId);
}

