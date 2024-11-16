package com.lana.system.dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.mybatis.dao.BaseDao;
import com.lana.system.entity.SysUserEntity;
import com.lana.system.entity.vo.query.SysUserQuery;
import com.lana.system.entity.vo.result.SysUserCopyResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {


    /**
     * 根据用户名获取用户实体。
     *
     * @param username 要查询的用户名，不能为空。
     * @return SysUserEntity 用户实体，如果不存在匹配的用户，则返回null。
     */
    default SysUserEntity getByUsername(String username){
        // 使用QueryWrapper构造查询条件，查询用户名等于传入参数的用户记录
        return this.selectOne(new QueryWrapper<SysUserEntity>().eq("username", username));
    }


    /**
     * 获取用户列表。
     *
     * @param page 分页参数，包含当前页和每页数量。
     * @param query 查询条件，用于筛选用户。
     * @return 返回用户列表的分页结果，列表中每个元素是SysUserResult类型的用户信息。
     */
    IPage<SysUserCopyResult> getLists(@Param("page") IPage<SysUserEntity> page, @Param("query") SysUserQuery query, @Param("DataScopeIgnore") boolean DataScopeIgnore);


    /**
     * 查询用户列表
     * @param subIdList
     * @return
     */
    List<Long> getUSerIds(@Param("subIdList") List<Long> subIdList);

    /**
     * 查询用户列表
     * @param orgId
     * @return
     */
    List<Long> getOnlyOrgIdList(Long orgId);
}
