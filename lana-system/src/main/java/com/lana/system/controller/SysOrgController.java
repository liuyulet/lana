package com.lana.system.controller;

import com.lana.base.syshandle.result.LanaResult;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.system.entity.vo.query.SysOrgQuery;
import com.lana.system.entity.vo.result.SysOrgResult;
import com.lana.system.entity.vo.save.SysOrgSave;
import com.lana.system.entity.vo.update.SysOrgUpdate;
import com.lana.system.service.SysOrgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/30 14:18
 */
@RestController
@RequestMapping("/sys/org")
@Tag(name = "机构管理")
public class SysOrgController {
    @Resource
    private SysOrgService sysOrgService;


    @GetMapping("/list")
    @Operation(summary = "列表")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('sys:org:list')")
    public LanaResult<List<SysOrgResult>> list(@ParameterObject @Valid SysOrgQuery query) {
        List<SysOrgResult> list = sysOrgService.getList(query);

        return LanaResult.ok(list);
    }


    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('sys:org:save')")
    public LanaResult<String> save(@RequestBody @Valid SysOrgSave saveVO) {
        sysOrgService.save(saveVO);

        return LanaResult.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('sys:org:update')")
    public LanaResult<String> update(@RequestBody @Valid SysOrgUpdate updateVO) {
        sysOrgService.update(updateVO);

        return LanaResult.ok();
    }

    @GetMapping("/delete")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('sys:org:delete')")
    public LanaResult<String> delete(@RequestParam("id") Long id) {
        sysOrgService.delete(id);

        return LanaResult.ok();
    }



}
