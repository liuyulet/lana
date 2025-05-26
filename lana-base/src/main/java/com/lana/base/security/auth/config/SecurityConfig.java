package com.lana.base.security.auth.config;

import com.lana.base.security.IgnoreUrls.IgnoreUrlsConfig;
import com.lana.base.security.auth.exception.SecurityAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/16 14:33
 */
@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
@EnableGlobalAuthentication
public class SecurityConfig {
    private final OncePerRequestFilter authenticationTokenFilter;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;



    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providerList = new ArrayList<>();
        providerList.add(daoAuthenticationProvider());
        ProviderManager providerManager = new ProviderManager(providerList);
        providerManager.setAuthenticationEventPublisher(new DefaultAuthenticationEventPublisher(applicationEventPublisher));
        return providerManager;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 忽略授权的地址列表
        List<String> permitList = IgnoreUrlsConfig.getPermitList();
        if (permitList != null && !permitList.isEmpty()) {
            String[] permits = permitList.toArray(new String[0]);
            http
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(permits).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception.authenticationEntryPoint(new SecurityAuthenticationEntryPoint()))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable);
            return http.build();
        }else {
            // 如果列表为空或null，进行合理的默认配置
            http
                    .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth -> auth
                            .anyRequest().authenticated()
                    )
                    .exceptionHandling(exception -> exception.authenticationEntryPoint(new SecurityAuthenticationEntryPoint()))
                    .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                    .csrf(AbstractHttpConfigurer::disable);
            return http.build();
        }
    }


    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

}
