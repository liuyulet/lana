package com.lana.modules.system.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Result;
import com.lana.modules.system.pojo.entity.SysUserDepartRoleEntity;
import com.lana.modules.system.service.SysStepService;
import com.lana.modules.system.service.SysUserDepartRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (SysUserDepartRole)表控制层
 *
 * @author liuyulet
 * @since 2022-10-04 18:21:58
 */
@RestController
@Api(tags = "用户组织角色信息接口")
@ApiSupport(author = "liuyulet")
@RequestMapping("/sys/UserDepartRole")
public class SysUserDepartRoleController extends AbstractController{
    /**
     * 服务对象
     */
    @Autowired
    private SysUserDepartRoleService sysUserDepartRoleService;
    @Resource
    private SysStepService sysStepService;

    /**
     * 用户组织角色信息接口
     */
    @ApiOperation(value = "用户组织角色信息接口列表", notes = "用户组织角色信息接口列表")
    @GetMapping("/getUserMessage")
    public Result list(@RequestParam Map<String, Object> params) {

        PageUtils page = sysUserDepartRoleService.queryPage(params);

        return Result.ok(page);
    }


    /**
     * 用户组织角色信息接口
     */
    @ApiOperation(value = "用户组织角色信息接口列表", notes = "用户组织角色信息接口列表")
    @GetMapping("/getstepNodeUser")
    public Result getstepNodeUser(@RequestParam Map<String, Object> params) {
        //获取
        List<Long> sss =sysStepService.getUserId(params.get("stepId"),params.get("stepNode"));
        params.put("userIds",sss);
        List<Map<String,Object>> page = sysUserDepartRoleService.getstepPage(sss);

        return Result.ok(page);
    }


}

