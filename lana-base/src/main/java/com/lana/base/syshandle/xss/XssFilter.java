package com.lana.base.syshandle.xss;

import com.lana.base.syshandle.xss.proties.XssProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @auther liuyulet
 * @date 2024/3/16 14:10
 */
@AllArgsConstructor
public class XssFilter extends OncePerRequestFilter {
    private final XssProperties properties;
    private final PathMatcher pathMatcher;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        filterChain.doFilter(new XssRequestWrapper(request), response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 放行不过滤的URL
        return properties.getIgnorePaths().stream().anyMatch(excludeUrl -> pathMatcher.match(excludeUrl, request.getRequestURI()));
    }

}
