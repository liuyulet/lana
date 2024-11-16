package com.lana.system.controller;

import cn.hutool.core.util.StrUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.system.entity.vo.query.SysAccountLoginQuery;
import com.lana.system.entity.vo.result.AccessTokenResult;
import com.lana.system.entity.vo.result.SysUserAuthDataResult;
import com.lana.system.entity.vo.save.SysUserSave;
import com.lana.system.service.SysAuthService;
import com.lana.system.service.SysUserService;
import com.lana.system.service.SysUserTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @auther liuyulet
 * @date 2024/3/21 15:56
 */
@Slf4j
@RestController
@RequestMapping("/sys/auth")
@ApiSupport(author = "liuyulet")
@Tag(name = "认证管理")
public class SysAuthController {

    @Resource
    private SysAuthService sysAuthService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private SysUserTokenService sysUserTokenService;

    /**
     * 账号密码登录接口。
     * 使用账号和密码进行登录验证，并返回用户令牌。
     * @param login 包含账号和密码信息的登录请求对象。
     * @return 返回登录结果，其中包含用户令牌信息。
     */
    @PostMapping("/login")
    @Operation(summary = "账号密码登录")
    public LanaResult<SysUserAuthDataResult> login(@RequestBody SysAccountLoginQuery login) {
        // 通过账号服务进行账号密码登录，返回用户令牌
        SysUserAuthDataResult token = sysAuthService.loginByAccount(login);
        // 返回登录成功的结果，包含用户令牌
        return LanaResult.ok(token);
    }


    @GetMapping("/ver")
    @Operation(summary = "获取最新版本号")
    public LanaResult<String> ver() {
        // 返回登录成功的结果，包含用户令牌
        return LanaResult.ok(sysAuthService.getVersion());
    }


    @PostMapping("/registerUser")
    @Operation(summary = "获取最新版本号")
    public LanaResult<String> registerUser(@RequestBody @Valid SysUserSave vo) {
        // 新增密码不能为空
        if (StrUtil.isBlank(vo.getPassword())) {
            return LanaResult.error("密码不能为空");
        }
        // 密码加密
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        // 保存
        sysUserService.registerUser(vo);

        return LanaResult.ok();
    }


    /**
     * 退出登录操作。
     * <p>
     * 接收用户的登出请求，通过调用sysUserTokenService的logout方法来实现用户的登出功能。
     * 不需要用户传递任何参数，只需通过HttpServletRequest获取当前请求的信息。
     * <p>
     * @param request HttpServletRequest对象，用于获取当前请求的信息。
     * @return 返回一个表示操作结果的Result对象，登出操作成功则返回一个状态为OK的结果。
     */
    @PostMapping("/logout")
    @Operation(summary = "退出登陆")
    public LanaResult<AccessTokenResult> logout(HttpServletRequest request) {
        // 调用服务层方法，处理用户登出逻辑
        sysUserTokenService.logout(request);
        // 返回成功响应
        return LanaResult.ok();
    }

}
