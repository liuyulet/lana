package com.lana.abutment.mqtthandle.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.abutment.mqtthandle.entity.vo.result.ProtocolsMqttResult;
import com.lana.abutment.mqtthandle.entity.vo.query.ProtocolsMqttQuery;
import com.lana.abutment.mqtthandle.entity.vo.result.RuleProtocolsMqttResult;
import com.lana.abutment.mqtthandle.entity.vo.save.ProtocolsMqttSave;
import com.lana.abutment.mqtthandle.service.ProtocolsMqttService;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.result.LanaResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.lana.abutment.mqtthandle.entity.vo.update.ProtocolsMqttUpdate;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @auther liuyulet
 * @date 2024/8/20 18:36
 */
@RestController
@RequestMapping("/abutment/mqtt")
@Tag(name = "mqtt协议")
@ApiSupport(author = "liuyulet")
public class ProtocolsMqttController {

    @Resource
    private ProtocolsMqttService protocolsMqttService;
    @GetMapping("/page")
    @Operation(summary = "查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('protocols:mqtt:page')")
    public LanaResult<List<ProtocolsMqttResult>> getMqttGroupPage(@ParameterObject @Valid ProtocolsMqttQuery query) {
        List<ProtocolsMqttResult> lanaPage = protocolsMqttService.getMqttGroupPage(query);
        return LanaResult.ok(lanaPage);
    }

    @GetMapping("/list")
    @Operation(summary = "情景联动协议查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('protocols:mqtt:page')")
    public LanaResult<List<RuleProtocolsMqttResult>> getMqttGroupList(@ParameterObject @Valid ProtocolsMqttQuery query) {
        List<RuleProtocolsMqttResult> lanaPage = protocolsMqttService.getMqttGroupList(query);
        return LanaResult.ok(lanaPage);
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    public LanaResult saveProtocolsMqttSave(@RequestBody @Valid ProtocolsMqttSave saveVO) {
        protocolsMqttService.saveProtocolsMqttSave(saveVO);
        return LanaResult.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public LanaResult<String> updateProtocolsMqtt(@RequestBody @Valid ProtocolsMqttUpdate updateVo) {
        protocolsMqttService.updateProtocolsMqtt(updateVo);
        return LanaResult.ok();
    }

    @GetMapping("/delete")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('abutment:protocols:del')")
    public LanaResult<String> deleteProtocolsMqtt(@RequestParam("id") Long id) {
        protocolsMqttService.deleteProtocolsMqtt(id);
        return LanaResult.ok();
    }



}
