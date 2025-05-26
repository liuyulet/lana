package com.lana.system.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.RedisCacheOps;
import com.lana.base.syshandle.exception.LanaException;
import com.lana.base.security.token.JwtUtils;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.base.security.token.properties.SecurityProperties;
import com.lana.base.security.token.user.UserDetail;
import com.lana.base.security.token.TokenUtils;
import com.lana.system.convert.SysUserTokenConvert;
import com.lana.system.dao.SysUserDao;
import com.lana.system.dao.SysUserTokenDao;
import com.lana.system.entity.SysUserTokenEntity;
import com.lana.system.entity.vo.result.SysUserTokenResult;
import com.lana.system.service.SysUserDetailsService;
import com.lana.system.service.SysUserTokenService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/25 11:40
 */
@Slf4j
@Service
public class SysUserTokenServiceImpl extends BaseServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {

    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private SysUserDetailsService sysUserDetailsService;
    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private RedisCacheOps redisCacheOps;
    @Resource
    private JwtUtils jwtUtils;

    /**
     * 生成token
     * @param user 用户ID
     * @return
     */
    @Override
    public SysUserTokenResult createToken(UserDetail user) {

        // todo 预留出来token刷新功能，
        // 生成token
        String accessToken = jwtUtils.generateToken(user);
        //生成刷新用的token
        String refreshAccessToken = jwtUtils.generateRefreshToken(user);
        //用户信息
        SysUserTokenEntity entity = new SysUserTokenEntity();
        entity.setUserId(user.getId());
        entity.setAccessToken(accessToken);
        entity.setRefreshToken(refreshAccessToken);
        // 过期时间
        Date now = new Date();
        entity.setAccessTokenExpire(DateUtil.offsetSecond(now, securityProperties.getAccessTokenExpire()));
        entity.setRefreshTokenExpire(DateUtil.offsetSecond(now, securityProperties.getRefreshTokenExpire()));

        // todo 加上redis开关，如果redis没有使用，则存库，相应的登陆认证都走数据库，如果开启了redis，则不做保存
        // 将信息存入数据库中
        SysUserTokenEntity tokenEntity = baseMapper.selectOne(new LambdaQueryWrapper<SysUserTokenEntity>().eq(SysUserTokenEntity::getUserId, user.getId()));
        if (tokenEntity == null) {
            baseMapper.insert(entity);
        } else {
            entity.setId(tokenEntity.getId());
            baseMapper.updateById(entity);
        }

        return SysUserTokenConvert.INSTANCE.convert(entity);
    }


    @Override
    public void logout(HttpServletRequest request) {

        //获取accessToken
        String accessToken = TokenUtils.getAccessToken(request);
        if (StringUtils.isBlank(accessToken)) {
            throw new LanaException("accessToken获取异常");
        }
        String userID = jwtUtils.extractUserId(accessToken);
        //1、清除缓存中的数据
        String key = CacheKeyBuilder.accessTokenKey(accessToken);
        redisCacheOps.delete(key);
        //2、清除SecurityContextHolder上下文
        SecurityContextHolder.clearContext();

    }

    /**
     * 根据角色ID，更新用户缓存权限
     * @param id
     */
    @Async
    @Override
    public void updateCacheAuthByRoleId(Long id) {
        // 根据角色ID，查询用户 access_token 列表
        List<String> accessTokenList = baseMapper.getOnlineAccessTokenListByRoleId(id, new Date());
        accessTokenList.forEach(this::updateCacheAuth);
    }

    @Async
    @Override
    public void updateCacheAuthByUserId(Long userId) {
        // 根据用户ID，查询用户 access_token 列表
        List<String> accessTokenList = baseMapper.getOnlineAccessTokenListByUserId(userId, new Date());
        accessTokenList.forEach(this::updateCacheAuth);
    }

    /**
     * 根据accessToken，更新Cache里面的用户权限
     *
     * @param accessToken access_token
     */
    private void updateCacheAuth(String accessToken) {
        String getAccessKey = CacheKeyBuilder.accessTokenKey(accessToken);
        UserDetail user = (UserDetail) redisCacheOps.get(getAccessKey);
        // 用户不存在
        if (user == null) {
            return;
        }
        // 查询过期时间
        String key = CacheKeyBuilder.accessTokenKey(accessToken);
        Long expire = redisCacheOps.getExpire(key);
        if (expire == null) {
            return;
        }
        // 设置用户权限信息
        sysUserDetailsService.getUserDetails(user);
        // 更新缓存
        String updateAccessKey = CacheKeyBuilder.accessTokenKey(accessToken);
        redisCacheOps.set(updateAccessKey, user, expire);


    }
}
