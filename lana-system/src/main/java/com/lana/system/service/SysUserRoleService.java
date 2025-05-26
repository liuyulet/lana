package com.lana.system.service;


import com.lana.base.mybatis.service.BaseService;
import com.lana.system.entity.SysUserRoleEntity;

import java.util.List;

public interface SysUserRoleService extends BaseService<SysUserRoleEntity> {

    /**
     * 角色ID列表
     * @param id
     * @return
     */
    List<Long> getRoleIdList(Long id);

    /**
     * 按用户 ID 列表删除
     * @param idList
     */
    void deleteByUserIdList(List<Long> idList);
    void deleteByUserIdList(Long userId);
    /**
     * 根据角色id、用户id列表，删除用户角色关系
     * @param roleId 角色id
     * @param userIdList 用户id列表
     */
    void deleteByUserIdList(Long roleId, List<Long> userIdList);

    /**
     * 根据角色id列表，删除用户角色关系
     * @param roleIdList 角色id
     */
    void deleteByRoleIdList(List<Long> roleIdList);

    /**
     * 分配角色给用户列表
     * @param roleId      角色ID
     * @param userIdList  用户ID列表
     */
    void saveUserList(Long roleId, List<Long> userIdList);

    /**
     * 保存用户角色
     * @param userId
     * @param userRole
     */
    void saveUserRole(Long userId, List<Long> userRole);
}
