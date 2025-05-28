package com.lana.system.controller;

import com.lana.base.syshandle.result.LanaResult;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.security.token.user.SecurityUser;
import com.lana.base.security.token.user.UserDetail;
import com.lana.system.entity.SysMenusEntity;
import com.lana.system.entity.dto.SysMenusDeleteDTO;
import com.lana.system.entity.vo.result.SysMenusNavResult;
import com.lana.system.entity.vo.result.SysMenusResult;
import com.lana.system.entity.vo.save.SysMenusSave;
import com.lana.system.entity.vo.update.SysMenusUpdate;
import com.lana.system.service.SysMenusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/30 15:11
 */
@RestController
@RequestMapping("/sys/menus")
@Tag(name = "菜单管理")
public class SysMenusController {

    @Resource
    private SysMenusService sysMenusService;

    @GetMapping("/nav")
    @Operation(summary = "菜单导航")
    @OptLog(type = OperateTypeEnum.QUERY)
  /*  @PreAuthorize("hasAuthority('sys:menus:nav')")*/
    public LanaResult<SysMenusNavResult> nav() {
        UserDetail user = SecurityUser.getUser();
        SysMenusNavResult navResult = sysMenusService.getNavMenuList(user);
        return LanaResult.ok(navResult);
    }

    @GetMapping("/list")
    @Operation(summary = "菜单列表")
    @Parameter(name = "type")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('sys:menus:list')")
    public LanaResult<List<SysMenusResult>> list(Integer type) {
        UserDetail user = SecurityUser.getUser();
        List<SysMenusResult> menu = sysMenusService.getMenuList(user);
        return LanaResult.ok(menu);
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('sys:menus:save')")
    public LanaResult save(@RequestBody @Valid SysMenusSave saveVO) {
        SysMenusEntity sysMenusEntity = sysMenusService.saveMenu(saveVO);
        return LanaResult.ok(sysMenusEntity.getId());
    }

    /**
     * @param uodateVO
     * @return
     */
    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('sys:menus:update')")
    public LanaResult<String> update(@RequestBody @Valid SysMenusUpdate uodateVO) {
        sysMenusService.updateByData(uodateVO);
        return LanaResult.ok();
    }



    /**
     * @param sysMenusDeleteDTO
     * @return
     */
    @PostMapping("/delete")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('sys:menus:delete')")
    public LanaResult<String> delete(@RequestBody SysMenusDeleteDTO sysMenusDeleteDTO) {
        sysMenusService.delete(sysMenusDeleteDTO.getIds());
        return LanaResult.ok();
    }

    @GetMapping("/roleList")
    @Operation(summary = "角色菜单分配列表")
    @Parameter(name = "type")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<List<String>> roleMenusList(@RequestParam("roleId") Long roleId) {
        UserDetail user = SecurityUser.getUser();
        List<String> menu = sysMenusService.getRoleMenusList(user,roleId);
        return LanaResult.ok(menu);
    }

}
