package com.lana.modules.system.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Result;
import com.lana.modules.system.pojo.entity.PalnItemEntity;
import com.lana.modules.system.service.PalnItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * (PalnItem)表控制层
 *
 * @author liuyulet
 * @since 2022-11-03 15:16:12
 */
@RestController
@Api(tags = "计划--任务维护")
@ApiSupport(author = "liuyulet")
@RequestMapping("/palnItem")
public class PalnItemController extends AbstractController{
    /**
     * 服务对象
     */
    @Resource
    private PalnItemService palnItemService;

    /**
     * 任务维护接口列表
     */
    @ApiOperation(value = "任务维护接口列表", notes = "任务维护接口列表")
    @GetMapping("/getPalnItem")
    public Result list(@RequestParam Map<String, Object> params) {

        PageUtils page = palnItemService.queryPage(params);

        return Result.ok(page);
    }

    /**
     * 删除任务项
     */

    @ApiOperation(value = "删除任务项", notes = "删除任务项")
    @GetMapping("/delPalnItem")
    public Result list(@RequestParam  Long id) {
        return Result.ok(palnItemService.removeById(id));
    }

    @ApiOperation(value = "新增计划任务项", notes = "新增计划任务项")
    @PostMapping("/addPalnItem")
    public Result list(@RequestBody PalnItemEntity palnItemEntity) {
        palnItemEntity.setCreateTime(new Date());
        palnItemService.save(palnItemEntity);

        return Result.ok();
    }



    @ApiOperation(value = "编辑计划任务项", notes = "编辑计划任务项")
    @PostMapping("/editPalnItem")
    public Result editPalnItem(@RequestBody PalnItemEntity palnItemEntity) {

        palnItemService.updateById(palnItemEntity);

        return Result.ok();
    }

}

