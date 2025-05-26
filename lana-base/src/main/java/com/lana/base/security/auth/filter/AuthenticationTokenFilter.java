package com.lana.base.security.auth.filter;

import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.RedisCacheOps;
import com.lana.base.security.token.JwtUtils;
import com.lana.base.security.token.user.UserDetail;
import com.lana.base.security.token.TokenUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**认证过滤器
 * @auther liuyulet
 * @date 2024/3/16 14:34
 */
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private JwtUtils jwtService;
    @Resource
    private RedisCacheOps redisCacheOps;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //获取accessToken
        String accessToken = TokenUtils.getAccessToken(request);
        // accessToken为空，表示未登录
        if (StringUtils.isBlank(accessToken)) {
            chain.doFilter(request, response);
            return;
        }
        //使用jwt解密获取userName，
        String username = jwtService.extractUsername(accessToken);

        //判断是否有效
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            //验证token是否有效，redis设置了失效时间，也就是说，存在即有效
            String key = CacheKeyBuilder.accessTokenKey(accessToken);
            UserDetail user = (UserDetail) redisCacheOps.get(key);
            if (user == null) {
                chain.doFilter(request, response);
                return;
            }
            // 用户存在
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            // 新建 SecurityContext
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
            chain.doFilter(request, response);
        }else {
            chain.doFilter(request, response);
            return;
        }
    }
}
