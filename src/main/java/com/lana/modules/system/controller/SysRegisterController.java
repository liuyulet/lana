package com.lana.modules.system.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.common.utils.Result;
import com.lana.modules.system.pojo.entity.SysUserEntity;
import com.lana.modules.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册接口
 *
 * @auther liuyulet
 * @date 2022/9/27 12:54
 */
@RestController
@Api(tags = "注册接口")
@ApiSupport(author = "liuyulet")
@RequestMapping("/sys/reg")
public class SysRegisterController extends AbstractController{
    @Autowired
    private SysUserService sysUserService;

    /**
     * 注册用户
     */
    @ApiOperation(value = "注册用户", notes = "注册用户")
    @PostMapping("/register")
    public Result login(@RequestBody SysUserEntity form) {

        sysUserService.saveUser(form);

        return Result.ok("注册成功");
    }
}
