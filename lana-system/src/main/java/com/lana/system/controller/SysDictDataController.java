package com.lana.system.controller;

import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.system.convert.SysDictDataConvert;
import com.lana.system.entity.SysDictDataEntity;
import com.lana.system.entity.vo.query.SysDictDataQuery;
import com.lana.system.entity.vo.result.SysDictDataResult;
import com.lana.system.entity.vo.save.SysDictDataSave;
import com.lana.system.entity.vo.update.SysDictDataUpdate;
import com.lana.system.service.SysDictDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @auther liuyulet
 * @date 2024/3/31 22:20
 */
@RestController
@RequestMapping("/sys/dic")
@Tag(name = "字典数据")
public class SysDictDataController {
    @Resource
    private SysDictDataService sysDictDataService;

    /**
     * 分页查询系统字典数据
     *
     * @param query 分页查询参数，包含查询条件和分页信息
     * @return 返回分页查询结果，其中包含系统字典数据列表
     */
    @GetMapping("/page")
    @Operation(summary = "分页")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('sys:dic:page')")
    public LanaResult<LanaPage<SysDictDataResult>> page(@ParameterObject @Valid SysDictDataQuery query) {
        // 调用服务进行分页查询
        LanaPage<SysDictDataResult> lanaPage = sysDictDataService.page(query);
        // 返回查询结果
        return LanaResult.ok(lanaPage);
    }


    @PostMapping("/saveDicData")
    @Operation(summary = "新增")
    @OptLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('sys:dic:save')")
    public LanaResult<String> save(@RequestBody @Valid SysDictDataSave saveVO) {
        sysDictDataService.save(saveVO);
        return LanaResult.ok();
    }
    /**
     * 修改字典数据
     */
    /**
     * @param uodateVO
     * @return
     */
    @PostMapping("/updateDicData")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('sys:dic:update')")
    public LanaResult<String> update(@RequestBody @Valid SysDictDataUpdate uodateVO) {
        SysDictDataEntity entity = SysDictDataConvert.INSTANCE.convert(uodateVO);
        sysDictDataService.updateById(entity);
        return LanaResult.ok();
    }

    @GetMapping("/deletDicData")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('sys:dic:delete')")
    public LanaResult<String> delete(@RequestParam("id") Long id) {
        // 调用服务层方法，执行删除操作
        sysDictDataService.removeById(id);
        return LanaResult.ok();
    }


}
