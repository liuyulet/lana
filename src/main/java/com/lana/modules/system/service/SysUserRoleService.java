
package com.lana.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.modules.system.pojo.entity.SysUserRoleEntity;


import java.util.HashMap;
import java.util.List;


/**
 * 用户与角色对应关系
 * @auther liuyulet
 * @date 2022/9/27 12:54
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);

}
