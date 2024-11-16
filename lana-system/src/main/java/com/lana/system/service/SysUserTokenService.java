package com.lana.system.service;

import com.lana.base.mybatis.service.BaseService;
import com.lana.base.security.token.user.UserDetail;
import com.lana.system.entity.SysUserTokenEntity;
import com.lana.system.entity.vo.result.SysUserTokenResult;
import jakarta.servlet.http.HttpServletRequest;

public interface SysUserTokenService extends BaseService<SysUserTokenEntity> {

    /**
     * 根据用户ID，生成用户Token
     *
     * @param user 用户
     * @return 用户Token
     */
    //SysUserTokenResult createToken(Long userId);
    SysUserTokenResult createToken(UserDetail user);


    /**
     * 退出登录
     * @param request
     */
    void logout(HttpServletRequest request);

    /**
     * 根据角色ID，更新用户缓存权限
     * @param id
     */
    void updateCacheAuthByRoleId(Long id);

    /**
     * 根据用户ID，更新用户缓存权限
     *
     * @param userId 用户ID
     */
    void updateCacheAuthByUserId(Long userId);
}
