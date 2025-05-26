package com.lana.system.dao;

import com.lana.base.mybatis.dao.BaseDao;
import com.lana.system.entity.SysMenusEntity;
import com.lana.system.entity.vo.result.SysMenusResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenusDao extends BaseDao<SysMenusEntity> {

    /**
     * 查询
     * @param
     * @return
     */
    List<SysMenusResult> getMenus();
    /**
     * 查询所有权限列表
     */
    List<String> getAuthorityList();
    /**
     * 查询用户权限列表
     */
    List<String> getUserAuthorityList(@Param("userId") Long id);

    List<String> getRoleMenusList(@Param("roleId") Long roleId);

    List<Long> getMenuIds(@Param("menus") List<String> menus);

    List<SysMenusResult> getUserMenuList(@Param("userId") Long id);
}
