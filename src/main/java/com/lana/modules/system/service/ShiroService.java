
package com.lana.modules.system.service;


import com.lana.modules.system.pojo.entity.SysUserEntity;
import com.lana.modules.system.pojo.entity.SysUserTokenEntity;

import java.util.Set;

/**
 * shiro相关接口
 * @auther liuyulet
 * @date 2022/9/27 12:54
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserEntity queryUser(Long userId);
}
