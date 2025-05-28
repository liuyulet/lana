package com.lana.rules.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.rules.entity.vo.query.RulesItemNodeQuery;
import com.lana.rules.entity.vo.save.RulesItemNodeSave;
import com.lana.rules.entity.vo.result.RulesItemNodeResult;
import com.lana.rules.service.RulesItemNodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:28
 */
@RestController
@RequestMapping("/rules/rulesItemNode")
@Tag(name = "规则节点")
@ApiSupport(author = "liuyulet")
public class RulesItemNodeController {

    @Resource
    private RulesItemNodeService rulesItemNodeService;


    @PostMapping("/saveAndUpdate")
    @Operation(summary = "规则信息设置")
    @OptLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('rules:rules:updateAndSave')")
    public LanaResult saveRulesItemNodeSave(@RequestBody @Valid RulesItemNodeSave saveRulesItemNodeSaveVO) {
        return rulesItemNodeService.SaveAndUpdate(saveRulesItemNodeSaveVO);
    }
    @PostMapping("/getRulesItem")
    @Operation(summary = "获取规则详情")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<RulesItemNodeResult> getRulesItemDetail(@RequestBody @Valid RulesItemNodeQuery queryVO) {
        RulesItemNodeResult rulesItemNodeResult = rulesItemNodeService.GetRulesItemDetail(queryVO);
        return LanaResult.ok(rulesItemNodeResult);
    }


}
