package com.lana.system.controller;

import com.lana.base.syshandle.result.LanaResult;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.system.convert.SysDictTypeConvert;
import com.lana.system.entity.SysDictTypeEntity;
import com.lana.system.entity.vo.result.SysDictTypeResult;
import com.lana.system.entity.vo.save.SysDictTypeSave;
import com.lana.system.entity.vo.update.SysDictTypeUpdate;
import com.lana.system.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/31 17:38
 */
@RestController
@RequestMapping("/sys/dict")
@Tag(name = "字典类型")
public class SysDictTypeController {

    @Resource
    private SysDictTypeService sysDictTypeService;


    @GetMapping("/list")
    @Operation(summary = "字典列表")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('sys:dict:list')")
    public LanaResult<List<SysDictTypeResult>> getList() {
        // 通过ID从服务中获取字典类型实体
        List<SysDictTypeEntity> entity = sysDictTypeService.getList();
        // 将实体转换为SysDictTypeResult对象，然后封装到Result对象中返回
        return LanaResult.ok(SysDictTypeConvert.INSTANCE.convertList(entity));
    }


    @PostMapping("/save")
    @Operation(summary = "新增")
    @OptLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('sys:dict:save')")
    public LanaResult<String> save(@RequestBody @Valid SysDictTypeSave saveVO) {
        sysDictTypeService.save(saveVO);
        return LanaResult.ok();
    }


    /**
     * @param uodateVO
     * @return
     */
    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('sys:dict:update')")
    public LanaResult<String> update(@RequestBody @Valid SysDictTypeUpdate uodateVO) {
        SysDictTypeEntity entity = SysDictTypeConvert.INSTANCE.convert(uodateVO);
        sysDictTypeService.updateById(entity);
        return LanaResult.ok();
    }


    @GetMapping("/deletDicType")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('sys:dict:delete')")
    public LanaResult<String> delete(@RequestParam("id") Long id) {
        // 调用服务层方法，执行删除操作
        sysDictTypeService.removeDictType(id);
        return LanaResult.ok();
    }

}
