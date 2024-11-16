package com.lana.rules.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.rules.entity.vo.query.RulesItemQuery;
import com.lana.rules.entity.vo.result.RulesItemResult;
import com.lana.rules.entity.vo.save.RulesItemSave;
import com.lana.rules.entity.vo.update.RulesItemUpdate;
import com.lana.rules.service.RulesItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    //支持编写规则后，生成规则文件，并且支持编写表达式。
    //如果新增的是脚本，则需要先保存生成后的脚本文件，在进行初始化并将其加入到缓存中，最后进行脚本文件信息保存到数据库中。
    //如果新增的是表达式，则进行初始化，再加入到缓存中，最后保存到数据库中。


    //建立设备---规则---动作表，



    @PostMapping("/updateAndSave")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.UPDATE)
    //@PreAuthorize("hasAuthority('rules:rulesItem:save')")
    public LanaResult updateAndSaveRulesItem(@RequestBody @Valid RulesItemUpdate updateVO) {
        rulesItemService.updateAndSaveRulesItem(updateVO);
        return LanaResult.ok();
    }




    @PostMapping("/getRules")
    @Operation(summary = "获取规则信息")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('rules:rulesItem:save')")
    public LanaResult<RulesItemResult> getRules(@RequestBody @Valid RulesItemQuery queryVO) {
        return LanaResult.ok(rulesItemService.getRules(queryVO));
    }



}
