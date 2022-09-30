/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.lana.modules.system.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.common.utils.Constant;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Result;
import com.lana.common.validator.Assert;
import com.lana.modules.system.pojo.dto.PasswordDTO;
import com.lana.modules.system.pojo.entity.SysUserEntity;
import com.lana.modules.system.service.SysUserRoleService;
import com.lana.modules.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * @auther liuyulet
 * @date 2022/9/27 12:54
 */

@RestController
@Api(tags = "用户信息接口")
@ApiSupport(author = "liuyulet")
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;


    /**
     * 所有用户列表
     */
    @ApiOperation(value = "用户列表", notes = "用户列表")
    @GetMapping("/getUser")
    public Result list(@RequestParam Map<String, Object> params) {

        PageUtils page = sysUserService.queryPage(params);

        return Result.ok(page);
    }


    /**
     * 获取登录的用户信息
     */
    @ApiOperation(value = "用户信息", notes = "用户信息")
    @GetMapping("/info")
    public Result info() {
        return Result.ok(getUser());
    }

    /**
     * 修改登录用户密码
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping("/password")
    public Result password(@RequestBody PasswordDTO form) {
        Assert.isBlank(form.getNewPassword(), "新密码不为能空");

        //sha256加密
        String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
        //sha256加密
        String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();

        //更新密码
        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return Result.error("原密码不正确");
        }

        return Result.ok();
    }

    /**
     * 用户信息
     */
    @ApiOperation(value = "用户详情", notes = "用户详情")
    @GetMapping("/info/{userId}")
    public Result info(@PathVariable("userId") Long userId) {
        SysUserEntity user = sysUserService.getById(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return Result.ok(user);
    }

    /**
     * 保存用户
     */
    @ApiOperation(value = "保存用户", notes = "保存用户")
    @PostMapping("/save")
    public Result save(@RequestBody SysUserEntity user) {

        sysUserService.saveUser(user);

        return Result.ok();
    }

    /**
     * 修改用户
     */
    @ApiOperation(value = "修改用户", notes = "修改用户")
    @PostMapping("/update")
    public Result update(@RequestBody SysUserEntity user) {

        sysUserService.update(user);

        return Result.ok();
    }

    /**
     * 删除用户
     */
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @GetMapping("/delete")
    public Result delete(@RequestParam Map<String, Object> params) {

        boolean stu = sysUserService.removeById(params.get("userId").toString());
        if(stu){
            return Result.ok();
        }else {
            return Result.error("删除失败");
        }

    }
}
