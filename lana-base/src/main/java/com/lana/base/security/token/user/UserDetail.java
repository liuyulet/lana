package com.lana.base.security.token.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lana.base.syshandle.exception.LanaException;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @auther liuyulet
 * @date 2024/3/16 14:37
 */
@Data
public class UserDetail implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private String realName;
    private String avatar;
    private Integer gender;
    private String email;
    private String mobile;
    private String signature;
    private Integer status;
    private Integer superAdmin;

    /**
     * 数据权限范围
     * <p>
     * null：表示全部数据权限
     * 本人数据默认查询
     */
    private List<Long> dataScopeList;
    /**
     * 帐户是否过期
     */
    private boolean isAccountNonExpired = true;
    /**
     * 帐户是否被锁定
     */
    private boolean isAccountNonLocked = true;
    /**
     * 密码是否过期
     */
    private boolean isCredentialsNonExpired = true;
    /**
     * 帐户是否可用
     */
    private boolean isEnabled = true;
    /**
     * 拥有权限集合
     */
    private Set<String> authoritySet;



    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoritySet.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        if (this.status == 0) {
            this.isEnabled = false;
            throw new LanaException("用户未启用，请联系管理员启用用户");
        }
        return this.isEnabled;
    }
}
