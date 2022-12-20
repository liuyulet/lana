package com.lana.modules.system.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Result;
import com.lana.modules.system.service.SysDemanHistroyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 需求变更历史表(SysDemanHistroy)表控制层
 *
 * @author liuyulet
 * @since 2022-10-06 13:55:12
 */
@Api(tags = "需求变更历史")
@ApiSupport(author = "liuyulet")
@RestController
@RequestMapping("/sysDemanHistroy")
public class SysDemanHistroyController extends  AbstractController{
    /**
     * 服务对象
     */
    @Resource
    private SysDemanHistroyService sysDemanHistroyService;

    /**
     * 所有需求列表
     */
    @ApiOperation(value = "需求列表", notes = "需求列表")
    @GetMapping("/getDeman")
    public Result list(@RequestParam Map<String, Object> params) {

        PageUtils page = sysDemanHistroyService.queryPage(params);

        return Result.ok(page);
    }


}

