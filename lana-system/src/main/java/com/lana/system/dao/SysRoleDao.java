package com.lana.system.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.mybatis.dao.BaseDao;
import com.lana.system.entity.SysRoleEntity;
import com.lana.system.entity.vo.query.SysRoleQuery;
import com.lana.system.entity.vo.result.SysRoleResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntity> {

    /**
     * 根据用户ID，获取用户最大的数据范围(即最小值)
     */
    Integer getDataScopeByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID，获取用户角色编码
     */
    List<String> geRoleCodeByUserId(@Param("userId") Long userId);

    /**
     * 角色分页
     * @param page
     * @param model
     * @return
     */
    //@DataScopeIgnore(放弃注解方案)
    IPage<SysRoleResult> getLists(@Param("page") IPage<SysRoleEntity> page,@Param("model") SysRoleQuery model,@Param("DataScopeIgnore") boolean DataScopeIgnore);

    /**
     * 修改数据权限
     * @param roleid
     * @param grid
     */
    void updateRole(@Param("roleid") Long roleid,@Param("grid") Integer grid);

    List<SysRoleEntity> getList(@Param("sysRoleQuery") SysRoleQuery sysRoleQuery,@Param("DataScopeIgnore") boolean DataScopeIgnore);
}
