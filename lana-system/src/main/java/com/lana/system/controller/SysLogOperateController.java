package com.lana.system.controller;

import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.system.entity.vo.query.SysLogOperateQuery;
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
    @Operation(summary = "分页")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<LanaPage<SysLogOperateResult>> page(@ParameterObject @Valid SysLogOperateQuery query) {
        LanaPage<SysLogOperateResult> lanaPage = sysLogOperateService.page(query);
        return LanaResult.ok(lanaPage);
    }

}
