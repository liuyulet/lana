/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.lana.modules.system.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.common.utils.Result;
import com.lana.modules.system.pojo.entity.SysRoleEntity;
import com.lana.modules.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 角色管理
 * @auther liuyulet
 * @date 2022/9/27 12:54
 */
@RestController
@Api(tags = "角色接口")
@ApiSupport(author = "liuyulet")
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 查询所有数据
     * @return 所有数据
     */
    @ApiOperation(value = "角色列表", notes = "角色列表")
    @GetMapping("/getRoleList")
    public Result selectAll() {
        List<SysRoleEntity> departs = sysRoleService.list();
        return Result.ok(departs);
    }

    /**
     * 保存角色
     * @return 保存角色
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存角色", notes = "保存角色")
    public Result saveRole(@RequestBody SysRoleEntity role) {
        sysRoleService.save(role);
        return Result.ok();
    }


    /**
     * 修改角色
     * @return 修改角色
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改角色", notes = "修改角色")
    public Result updateRole(@RequestBody SysRoleEntity role) {
        sysRoleService.updateById(role);
        return Result.ok();
    }

    /**
     * 删除角色
     * @return 删除角色
     */
    @PostMapping("/delRole")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    public Result delRole(@RequestBody Long[] userIds) {
        sysRoleService.removeByIds(Arrays.asList(userIds));
        return Result.ok();
    }

}
