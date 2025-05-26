package com.lana.system.service.impl.security;

import com.lana.system.convert.UserCovertBasic;
import com.lana.system.dao.SysUserDao;
import com.lana.system.entity.SysUserEntity;
import com.lana.system.service.SysUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @auther liuyulet
 * @date 2024/3/17 16:55
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SysUserDetailsService sysUserDetailsService;
    private final SysUserDao sysUserDao;


    /**
     *
     * 入口：Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
     * 过程：了解参考AuthenticationManager 验证原理分析和AuthenticationManager 的 authentication 过程
     * 结果：查询查询用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserEntity userEntity = sysUserDao.getByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        //查询用户权限信息
        return sysUserDetailsService.getUserDetails(UserCovertBasic.INSTANCE.convertDetail(userEntity));
    }
}
