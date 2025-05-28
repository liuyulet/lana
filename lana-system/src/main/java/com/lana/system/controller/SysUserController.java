package com.lana.system.controller;

import cn.hutool.core.util.StrUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.security.token.user.SecurityUser;
import com.lana.system.entity.SysUserEntity;
import com.lana.system.entity.vo.query.SysUserQuery;
import com.lana.system.entity.vo.result.SysUserPasswordResult;
import com.lana.system.entity.vo.result.SysUserResult;
import com.lana.system.entity.vo.save.SysUserSave;
import com.lana.system.entity.vo.update.SysUserUpdate;
import com.lana.system.entity.vo.update.SysUserUpdatePassword;
import com.lana.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/20 16:54
 */
@Slf4j
@RestController
@RequestMapping("/sys/user")
@ApiSupport(author = "liuyulet")
@Tag(name = "用户管理")
public class SysUserController {


    @Resource
    private SysUserService sysUserService;
    @Resource
    private PasswordEncoder passwordEncoder;


    /**
     * 用户数据分页查询
     * @param query
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "分页")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('sys:user:page')")
    public LanaResult<LanaPage<SysUserResult>> page(@ParameterObject @Valid SysUserQuery query) {
        LanaPage<SysUserResult> lanaPage = sysUserService.page(query);
        return LanaResult.ok(lanaPage);
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('sys:user:save')")
    public LanaResult<String> save(@RequestBody @Valid SysUserSave vo) {
        // 新增密码不能为空
        if (StrUtil.isBlank(vo.getPassword())) {
            return LanaResult.error("密码不能为空");
        }
        // 密码加密
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        // 保存
        sysUserService.saveUserOrgRole(vo);

        return LanaResult.ok();
    }

    @PostMapping("/resetPassword")
    @Operation(summary = "重置为默认密码")
    @OptLog(type = OperateTypeEnum.UPDATE)
    //@PreAuthorize("hasAuthority('sys:user:password')")
    public LanaResult<String> password(@RequestBody @Valid SysUserPasswordResult vo) {
        SysUserEntity sysUserEntity = sysUserService.getByIdUser(vo.getId());
        sysUserEntity.setPassword(passwordEncoder.encode("123456"));
        // 修改密码
        sysUserService.updateById(sysUserEntity);
        return LanaResult.ok();
    }




    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('sys:user:update')")
    public LanaResult<String> update(@RequestBody @Valid SysUserUpdate vo) {
        // 如果密码不为空，则进行加密处理
        if (StrUtil.isBlank(vo.getPassword())) {
            vo.setPassword(null);
        } else {
            vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        }

        sysUserService.updateByUserId(vo);

        return LanaResult.ok();
    }

    @PostMapping("/delete")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public LanaResult<String> delete(@RequestBody List<Long> idList) {
        Long userId = SecurityUser.getUserId();
        if (idList.contains(userId)) {
            return LanaResult.error("不能删除当前登录用户");
        }
        sysUserService.delete(idList);

        return LanaResult.ok();
    }

    @PostMapping("/updateMySelf")
    @Operation(summary = "修改个人用户信息")
    @OptLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('serve:user:save')")
    public LanaResult updateMySelf(@RequestBody @Valid SysUserUpdate sysUserUpdate) {
        SysUserResult sysUserResult = sysUserService.updateMySelf(sysUserUpdate);
        return LanaResult.ok();
    }

    @PostMapping("/updateMySelfPassword")
    @Operation(summary = "修改个人密码")
    @OptLog(type = OperateTypeEnum.UPDATE)

    @PreAuthorize("hasAuthority('serve:password:save')")
    public LanaResult<String> password(@RequestBody @Valid SysUserUpdatePassword sysUserUpdatePassword) {
        // 修改密码
        String result = sysUserService.updateMySelfPassword(sysUserUpdatePassword);
        return LanaResult.ok(result);
    }

}
