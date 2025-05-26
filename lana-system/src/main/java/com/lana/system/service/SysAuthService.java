package com.lana.system.service;


import com.lana.system.entity.vo.query.SysAccountLoginQuery;
import com.lana.system.entity.vo.result.AccessTokenResult;
import com.lana.system.entity.vo.result.SysUserAuthDataResult;
import com.lana.system.entity.vo.result.SysUserTokenResult;

/**
 * 登陆认证
 */
public interface SysAuthService {

    /**
     * 账号密码登录
     *
     * @param login 登录信息
     */
    SysUserAuthDataResult loginByAccount(SysAccountLoginQuery login);


    String getVersion();
}
