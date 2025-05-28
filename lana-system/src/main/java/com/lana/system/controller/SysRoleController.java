package com.lana.system.controller;

import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.system.entity.vo.query.SysRoleQuery;
import com.lana.system.entity.vo.result.SysRoleResult;
import com.lana.system.entity.vo.save.SysRoleSave;
import com.lana.system.entity.vo.update.SysRoleDataScopeUpdate;
import com.lana.system.entity.vo.update.SysRoleMenusUpdate;
import com.lana.system.entity.vo.update.SysRoleUpdate;
import com.lana.system.service.*;
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
 * @date 2024/3/28 18:00
 */
@RestController
@RequestMapping("/sys/role")
@Tag(name = "角色管理")
public class    SysRoleController {

    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysMenusService sysMenuService;


    @GetMapping("/page")
    @Operation(summary = "分页")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('sys:role:page')")
    public LanaResult<LanaPage<SysRoleResult>> page(@ParameterObject @Valid SysRoleQuery query) {
        LanaPage<SysRoleResult> lanaPage = sysRoleService.page(query);
        return LanaResult.ok(lanaPage);
    }

    @GetMapping("/list")
    @Operation(summary = "列表")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<List<SysRoleResult>> list() {
        List<SysRoleResult> list = sysRoleService.getList(new SysRoleQuery());
        return LanaResult.ok(list);
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('sys:role:save')")
    public LanaResult<String> save(@RequestBody @Valid SysRoleSave saveVO) {
        sysRoleService.save(saveVO);
        return LanaResult.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('sys:role:update')")
    public LanaResult<String> update(@RequestBody @Valid SysRoleUpdate updateVo) {
        sysRoleService.update(updateVo);
        return LanaResult.ok();
    }



    @PostMapping("/roleLinkMenus")
    @Operation(summary = "角色绑定菜单、数据权限、首页模块权限")
    //@OperateLog(type = OperateTypeEnum.UPDATE)
    @OptLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('sys:role:roleLinkMenus')")
    public LanaResult<String> roleLinkMenus(@RequestBody @Valid SysRoleMenusUpdate sysRoleMenusUpdate) {
        sysRoleService.roleLinkMenus(sysRoleMenusUpdate);
        return LanaResult.ok();
    }


    @PutMapping("/data-scope")
    @Operation(summary = "数据权限")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public LanaResult<String> dataScope(@RequestBody @Valid SysRoleDataScopeUpdate updateVO) {
        sysRoleService.dataScope(updateVO);
        return LanaResult.ok();
    }

    @GetMapping("/delete")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public LanaResult<String> delete(@RequestParam("id") List<Long> idList) {
        sysRoleService.delete(idList);

        return LanaResult.ok();
    }

}
