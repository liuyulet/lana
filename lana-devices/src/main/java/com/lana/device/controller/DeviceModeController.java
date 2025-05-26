package com.lana.device.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;

import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.device.entity.vo.result.DeviceModeResult;
import com.lana.device.entity.vo.save.DeviceItemModeSave;
import com.lana.device.entity.vo.update.DeviceModeUpdate;
import com.lana.device.service.DeviceModeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @auther liuyulet
 * @date 2024/3/30 14:18
 */
@RestController
@RequestMapping("/devices/deviceMode")
@Tag(name = "设备物模型")
@ApiSupport(author = "liuyulet")
public class DeviceModeController {

    @Resource
    private DeviceModeService deviceModeService;

    @GetMapping("/list")
    @Operation(summary = "列表查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<List<DeviceModeResult>> getDeviceItemModeList(@RequestParam("id") Long id) {
        List<DeviceModeResult> lanaList = deviceModeService.getDeviceItemModeList(id);
        return LanaResult.ok(lanaList);
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    public LanaResult saveDeviceModes(@RequestBody @Valid DeviceItemModeSave saveVO) {
        deviceModeService.saveDeviceModes(saveVO);
        return LanaResult.ok();
    }
    @GetMapping("/contoleList")
    @Operation(summary = "控制属性列表查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<List<DeviceModeResult>> getDeviceContoleList(@RequestParam("id") Long id) {
        List<DeviceModeResult> lanaList = deviceModeService.getDeviceContoleList(id);
        return LanaResult.ok(lanaList);
    }

    @PostMapping("/saveContoleModel")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public LanaResult saveContoleModel(@RequestBody @Valid DeviceModeUpdate updataVO) {
        deviceModeService.saveContoleModel(updataVO);
        return LanaResult.ok();
    }



}
