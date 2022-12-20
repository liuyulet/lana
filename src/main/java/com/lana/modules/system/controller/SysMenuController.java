package com.lana.modules.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;

import com.lana.common.utils.Result;
import com.lana.modules.system.pojo.dto.MenuForRoleDTO;
import com.lana.modules.system.pojo.dto.UserForDepartDTO;
import com.lana.modules.system.pojo.entity.SysMenuEntity;
import com.lana.modules.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;



/**
 * 菜单管理(SysMenu)表控制层
 *
 * @author liuyulet
 * @since 2022-10-05 15:32:24
 */
@Api(tags = "导航栏菜单")
@ApiSupport(author = "liuyulet")
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysMenuService sysMenuService;


    /**
     * 导航菜单
     */
    @ApiOperation(value = "角色绑定菜单--->查询菜单列表", notes = "传入roleId，根据roleId查询出全部的菜单列表：AllMenu，已经选择的菜单列表：HavaMenu")
    @GetMapping("/navList")
    public Result navList(@RequestParam Long roleId){
        Result<JSONObject> result = new Result<JSONObject>();
        JSONObject re = new JSONObject();
        //查询当前角色的菜单
        List<SysMenuEntity> AllMenu = sysMenuService.list();
        //查询已经授权的菜单
        List<HashMap<String,Object>> HavaMenu = sysMenuService.getMenurole(roleId);
        re.put("AllMenu",AllMenu);
        re.put("HavaMenu",HavaMenu);
        result.setCode(200);
        result.setMessage("成功");
        result.setResult(re);
        return result;
    }


    /**
     * 角色绑定菜单
     *
     * @return 角色绑定菜单
     */
    @ApiOperation(value = "角色绑定菜单", notes = "角色绑定菜单")
    @PostMapping("/roleForMenu")
    public Result userForDepart(@RequestBody MenuForRoleDTO menuForRoleDTO) {
        //将数据更新到用户和组织机构中间表
        sysMenuService.userForDepart(menuForRoleDTO);
        return Result.ok();
    }


    /**
     * 导航菜单
     */
    @ApiOperation(value = "根据用户id查询所拥有的菜单权限", notes = "根据用户id查询所拥有的菜单权限")
    @GetMapping("/nav")
    public Result nav(@RequestParam(required=true) String userid){
        //根据用户信息查询可以看到哪些菜单
        Result<JSONObject> result = new Result<JSONObject>();
        JSONObject re = new JSONObject();
        List<SysMenuEntity> resulrt = sysMenuService.getNavData(userid);
        re.put("menus",resulrt);
        result.setCode(200);
        result.setMessage("成功");
        result.setResult(re);
        return result;
    }


}

