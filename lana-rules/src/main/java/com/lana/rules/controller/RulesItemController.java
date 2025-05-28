package com.lana.rules.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.rules.entity.vo.query.RulesItemPageQuery;
import com.lana.rules.entity.vo.query.RulesItemQuery;
import com.lana.rules.entity.vo.result.RulesItemResult;
import com.lana.rules.entity.vo.save.RulesItemSave;
import com.lana.rules.entity.vo.update.RulesItemUpdate;
import com.lana.rules.service.RulesItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:28
 */
@RestController
@RequestMapping("/rules/rulesItem")
@Tag(name = "规则实例")
@ApiSupport(author = "liuyulet")
public class RulesItemController {
    @Resource
    private RulesItemService rulesItemService;
    @GetMapping("/page")
    @Operation(summary = "规则实例分页查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<LanaPage<RulesItemResult>> getPage(@ParameterObject @Valid RulesItemPageQuery query) {
        LanaPage<RulesItemResult> lanaPage = rulesItemService.getRulesPage(query);
        return LanaResult.ok(lanaPage);
    }


    @PostMapping("/save")
    @Operation(summary = "规则实例新增")
    @OptLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('rules:rules:save')")
    public LanaResult saveRulesItem(@RequestBody @Valid RulesItemSave saveVO) {
        rulesItemService.saveRulesItem(saveVO);
        return LanaResult.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "规则实例修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    //@PreAuthorize("hasAuthority('rules:rulesItem:save')")
    @PreAuthorize("hasAuthority('rules:rules:update')")
    public LanaResult updateRulesItem(@RequestBody @Valid RulesItemUpdate updateVO) {
        rulesItemService.updateRulesItem(updateVO);
        return LanaResult.ok();
    }

    @GetMapping("/delete")
    @Operation(summary = "规则实例删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('rules:rules:delete')")
    public LanaResult deleteRulesItem(@RequestParam("id") Long id) {
        rulesItemService.deleteRulesItem(id);
        return LanaResult.ok();
    }

    /**
     * 该方法暂时弃用
     * @param
     * @return
     */
/*  @PostMapping("/updateAndSave")
    @Operation(summary = "新增/修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    //@PreAuthorize("hasAuthority('rules:rulesItem:save')")
    public LanaResult updateAndSaveRulesItem(@RequestBody @Valid RulesItemUpdate updateVO) {
        rulesItemService.updateAndSaveRulesItem(updateVO);
        return LanaResult.ok();
    }*/




/*    @PostMapping("/getRules")
    @Operation(summary = "获取规则信息")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('rules:rulesItem:save')")
    public LanaResult<RulesItemResult> getRules(@RequestBody @Valid RulesItemQuery queryVO) {
        return LanaResult.ok(rulesItemService.getRules(queryVO));
    }*/




/*    @PostMapping("/roleSeting")
    @Operation(summary = "规则信息设置")
    @OptLog(type = OperateTypeEnum.INSERT)
    //@PreAuthorize("hasAuthority('rules:rulesItem:save')")
    public LanaResult saveRulesItemNodeSave(@RequestBody @Valid RulesItemNodeSave saveRulesItemNodeSaveVO) {
        rulesItemService.saveRulesItemNodeSave(saveRulesItemNodeSaveVO);
        return LanaResult.ok();
    }
    @PostMapping("/getRulesItem")
    @Operation(summary = "获取规则详情")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('rules:rulesItem:save')")
    public LanaResult<RulesItemNodeQuert> getRulesItemDetail(@RequestBody @Valid RulesItemNodeQuery queryVO) {
        RulesItemNodeQuert rulesItemNodeQuert = rulesItemService.getRulesItemDetail(queryVO);
        if(rulesItemNodeQuert!=null){
            return LanaResult.ok(rulesItemNodeQuert);
        }else {
            return LanaResult.ok();
        }
    }
    */
}
