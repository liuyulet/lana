package com.lana.system.service.impl;

import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.RedisCacheOps;
import com.lana.base.syshandle.exception.LanaException;
import com.lana.base.security.token.properties.SecurityProperties;
import com.lana.base.security.token.user.UserDetail;
import com.lana.system.entity.vo.query.SysAccountLoginQuery;
import com.lana.system.entity.vo.result.SysUserAuthDataResult;
import com.lana.system.entity.vo.result.SysUserTokenResult;
import com.lana.system.entity.vo.result.UserInfoResult;
import com.lana.system.service.SysAuthService;
import com.lana.system.service.SysCaptchaService;
import com.lana.system.service.SysUserTokenService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


/**
 * @auther liuyulet
 * @date 2024/3/25 11:33
 */
@Slf4j
@Service
public class SysAuthServiceImpl implements SysAuthService {
    @Resource
    private SysCaptchaService sysCaptchaService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private SysUserTokenService sysUserTokenService;
    @Resource
    private RedisCacheOps redisCacheOps;
    @Resource
    private SecurityProperties securityProperties;

    @Value("${spring.captchaEnabled}")
    private Boolean captchaEnabled;
    @Value("${lana.security.aseSecretKey}")
    private String aseSecretKey;
    @Value("${lana.version}")
    private String version;

    @Override
    public SysUserAuthDataResult loginByAccount(SysAccountLoginQuery login) {

        if(captchaEnabled){
            boolean flag = sysCaptchaService.validate(login.getKey(), login.getCaptcha());
            if (!flag) {
                throw new LanaException("验证码错误");
            }
        }
        Authentication authentication;
        try {
            // 用户认证
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (BadCredentialsException e) {
            throw new LanaException("用户名或密码错误");
        }

        if (authentication != null) {
        UserDetail user = (UserDetail) authentication.getPrincipal();
            // 用户信息
            // 生成 accessToken,并将数据存入数据库中
            SysUserTokenResult userTokenResult = sysUserTokenService.createToken(user);
            // 返回数据
            SysUserAuthDataResult sysUserAuthDataResult = new SysUserAuthDataResult();
            // 保存到缓存中，缓存时间使用失效时间，到期后刷新token
            String key = CacheKeyBuilder.accessTokenKey(userTokenResult.getAccessToken());
            redisCacheOps.set(key, user, securityProperties.getAccessTokenExpire());

            sysUserAuthDataResult.setToken(userTokenResult.getAccessToken());
            sysUserAuthDataResult.setRefreshToken(userTokenResult.getRefreshToken());
            sysUserAuthDataResult.setAccessTokenExpire(userTokenResult.getAccessTokenExpire());
            sysUserAuthDataResult.setRefreshTokenExpire(userTokenResult.getRefreshTokenExpire());

            UserInfoResult userInfoResult = new UserInfoResult();
            userInfoResult.setUserId(user.getId());
            userInfoResult.setUserName(user.getUsername());
            userInfoResult.setDashboard("0");
            userInfoResult.setGender(user.getGender());
            userInfoResult.setRealName(user.getRealName());
            userInfoResult.setSignature(user.getSignature());
            userInfoResult.setAvatar(user.getAvatar());
            userInfoResult.setEmail(user.getEmail());
            userInfoResult.setMobile(user.getMobile());
            userInfoResult.setRole(user.getAuthoritySet());


            sysUserAuthDataResult.setUserInfo(userInfoResult);

            return sysUserAuthDataResult;
        } else {
            // 处理未认证的情况
            throw new LanaException("authentication is null");
        }

    }


    @Override
    public String getVersion() {
        return version;
    }
}
