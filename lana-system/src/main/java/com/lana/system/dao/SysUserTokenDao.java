package com.lana.system.dao;

import com.lana.base.mybatis.dao.BaseDao;
import com.lana.system.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface SysUserTokenDao extends BaseDao<SysUserTokenEntity> {


    /**
     * 根据用户ID，查询在线用户 access_token 列表
     * @param roleId
     * @param time
     * @return
     */
    List<String> getOnlineAccessTokenListByRoleId(@Param("roleId") Long roleId, @Param("time") Date time);

    /**
     * 根据用户ID，查询在线用户 access_token 列表
     *
     * @param userId 用户ID
     * @param time   当前时间
     * @return 返回 access_token 列表
     */
    List<String> getOnlineAccessTokenListByUserId(@Param("userId") Long userId, @Param("time") Date time);
}
