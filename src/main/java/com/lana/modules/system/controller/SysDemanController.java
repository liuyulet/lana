package com.lana.modules.system.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Result;
import com.lana.modules.system.service.SysDemanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (SysDeman)表控制层
 *
 * @author liuyulet
 * @since 2022-09-30 11:25:38
 */
@RestController
@Api(tags = "需求信息接口")
@ApiSupport(author = "liuyulet")
@RequestMapping("/sysDeman")
public class SysDemanController  extends  AbstractController{
    /**
     * 服务对象
     */
    @Resource
    private SysDemanService sysDemanService;
    /**
     * 所有需求列表
     */

    @ApiOperation(value = "需求列表", notes = "需求列表")
    @GetMapping("/getDeman")
    public Result list(@RequestParam Map<String, Object> params) {

        PageUtils page = sysDemanService.queryPage(params);

        return Result.ok(page);
    }

}

