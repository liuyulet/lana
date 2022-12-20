package com.lana.modules.system.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Result;
import com.lana.modules.system.pojo.dto.UserForDepartDTO;
import com.lana.modules.system.pojo.entity.SysDepart;
import com.lana.modules.system.service.SysDepartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 组织机构表(SysDepart)表控制层
 *
 * @author liuyulet
 * @since 2022-09-29 10:02:34
 */
@Api(tags = "组织机构接口")
@ApiSupport(author = "liuyulet")
@RestController
@RequestMapping("/sysDepart")
public class SysDepartController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysDepartService sysDepartService;

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @ApiOperation(value = "组织机构列表", notes = "组织机构列表")
    @GetMapping("/getDepart")
    public Result selectAll() {
        List<SysDepart> departs = sysDepartService.list();
        return Result.ok(departs);
    }


    /**
     * 详细信息
     *
     * @return 详细信息
     */
    @ApiOperation(value = "组织机构详细信息", notes = "组织机构详细信息")
    @PostMapping("/info/{Id}")
    public Result addDepart(@PathVariable("Id") Long Id) {
        SysDepart departs = sysDepartService.getById(Id);
        return Result.ok(departs);
    }

    /**
     * 新增
     *
     * @return 新增
     */
    @ApiOperation(value = "新增组织机构", notes = "新增组织机构")
    @PostMapping("/addDepart")
    public Result addDepart(@RequestBody SysDepart sysDepart) {
        sysDepart.setCreateTime(new Date());
        sysDepartService.save(sysDepart);
        return Result.ok();
    }

    /**
     * 删除组织机构
     *
     * @return 删除组织机构
     */
    @ApiOperation(value = "删除组织机构", notes = "删除组织机构")
    @GetMapping("/delDepart")
    public Result delDepart(@RequestParam Long userIds) {
        sysDepartService.removeById(userIds);
        return Result.ok();
    }

    /**
     * 修改机构列表
     *
     * @return 修改机构列表
     */
    @ApiOperation(value = "修改机构列表", notes = "修改机构列表")
    @PostMapping("/updateDepart")
    public Result updateDepart(@RequestBody SysDepart sysDepart) {
        sysDepartService.updateById(sysDepart);
        return Result.ok();
    }

    /**
     * 机构绑定人员
     *
     * @return 机构绑定人员
     */
    @ApiOperation(value = "机构绑定人员", notes = "机构绑定人员")
    @PostMapping("/userForDepart")
    public Result userForDepart(@RequestBody UserForDepartDTO userForDepartDTO) {
        //将数据更新到用户和组织机构中间表
        sysDepartService.userForDepart(userForDepartDTO);
        return Result.ok();
    }


}

