package com.lana.system.controller;

import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.system.entity.vo.query.SysLogOperateQuery;
import com.lana.system.entity.vo.query.SysLogSysOperateQuery;
import com.lana.system.entity.vo.result.SysLogOperateResult;
import com.lana.system.service.SysLogOperateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * @auther liuyulet
 * @date 2024/3/30 17:06
 */
@RestController
@RequestMapping("/sys/log/operate")
@Tag(name = "操作日志")
public class SysLogOperateController {

    @Resource
    private SysLogOperateService sysLogOperateService;


    @GetMapping("/page")
    @Operation(summary = "个人操作日志分页")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<LanaPage<SysLogOperateResult>> page(@ParameterObject @Valid SysLogOperateQuery query) {
        LanaPage<SysLogOperateResult> lanaPage = sysLogOperateService.page(query);
        return LanaResult.ok(lanaPage);
    }



    @GetMapping("/sysPage")
    @Operation(summary = "系统操作日志分页")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<LanaPage<SysLogOperateResult>> sysPage(@ParameterObject @Valid SysLogSysOperateQuery query) {
        if(query.getOperateType()!=null){
            return LanaResult.ok(sysLogOperateService.sysPage(query));
        }else {
            query.setOperateType(11);
            return LanaResult.ok(sysLogOperateService.sysPage(query));
        }


    }

    @GetMapping("/sysRulesPage")
    @Operation(summary = "情景模式日志分页查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<LanaPage<SysLogOperateResult>> sysRulesPage(@ParameterObject @Valid SysLogSysOperateQuery query) {
        if(query.getOperateType()!=null){
            return LanaResult.ok(sysLogOperateService.sysPage(query));
        }else {
            query.setOperateType(9);
            return LanaResult.ok(sysLogOperateService.sysPage(query));
        }


    }


    @GetMapping("/sysRulesWarnings")
    @Operation(summary = "预警消息日志分页查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<LanaPage<SysLogOperateResult>> sysRulesWarnings(@ParameterObject @Valid SysLogSysOperateQuery query) {
        if(query.getOperateType()!=null){
            return LanaResult.ok(sysLogOperateService.sysPage(query));
        }else {
            query.setOperateType(5);
            return LanaResult.ok(sysLogOperateService.sysPage(query));
        }


    }

}
