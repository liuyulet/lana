package com.lana.device.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.device.entity.DeviceControlDataEntity;
import com.lana.device.entity.vo.query.DeviceControlDataQuery;
import com.lana.device.entity.vo.result.DeviceControlDataResult;
import com.lana.device.entity.vo.save.DeviceControlDataSave;
import com.lana.device.service.DeviceControlDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/30 14:18
 */
@RestController
@RequestMapping("/devices/deviceControlData")
@Tag(name = "设备功能")
@ApiSupport(author = "liuyulet")
public class DeviceControlDataController {

    @Resource
    private DeviceControlDataService deviceControlDataService;


    @GetMapping("/list")
    @Operation(summary = "设备功能-列表")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<List<DeviceControlDataResult>> list(@ParameterObject @Valid DeviceControlDataQuery query) {
        List<DeviceControlDataResult> list = deviceControlDataService.getDeviceControlDataList(query);
        return LanaResult.ok(list);
    }


    /**
     * 新增
     */
    @PostMapping("/saveOrUpdate")
    @Operation(summary = "设备功能-保存/修改")
    @OptLog(type = OperateTypeEnum.INSERT)
    public LanaResult<DeviceControlDataEntity> save(@RequestBody @Valid DeviceControlDataSave saveVO) {
        return LanaResult.ok(deviceControlDataService.saveDeviceControl(saveVO));
    }

    /**
     * 修改
     */
/*    @PostMapping("/update")
    @Operation(summary = "暂时不用，自己写前端，双向迁就....")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public LanaResult update(@RequestBody @Valid DeviceControlDataUpdate updateVO) {
        deviceControlDataService.updateDeviceControlData(updateVO);
        return LanaResult.ok();
    }*/
    /**
     * 删除
     */
    @GetMapping("/delete")
    @Operation(summary = "设备功能-删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    public LanaResult delete(@RequestParam("id") Long id) {
        deviceControlDataService.deleteDeviceControlData(id);
        return LanaResult.ok();
    }
}
