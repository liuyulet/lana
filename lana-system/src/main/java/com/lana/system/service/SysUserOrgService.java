package com.lana.system.service;


import com.lana.base.mybatis.service.BaseService;
import com.lana.system.entity.SysUserOrgEntity;

import java.util.List;

public interface SysUserOrgService extends BaseService<SysUserOrgEntity> {


    /**
     * 保存用户组织部门
     * @param userId
     * @param userOrg
     */
    void saveUserOrg(Long userId, List<Long> userOrg);

    /**
     * 删除用户部门
     * @param idList
     */
    void deleteByUserIdList(List<Long> idList);

    /**
     * 删除用户部门
     * @param userId
     */
    void deleteByUserIdList(Long userId);
}
