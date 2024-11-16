package com.lana.device.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.device.entity.vo.result.DeviceProductModeResult;
import com.lana.device.service.DeviceProductModeService;
import com.lana.device.entity.vo.save.DeviceProductModeSave;
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
@RequestMapping("/devices/deviceProductMode")
@Tag(name = "设备产品物模型")
@ApiSupport(author = "liuyulet")
public class DeviceProductModeController {
    @Resource
    private DeviceProductModeService deviceProductModeService;


    @GetMapping("/list")
    @Operation(summary = "列表查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<List<DeviceProductModeResult>> getProductModePage(@RequestParam("id") Long id) {
        List<DeviceProductModeResult> lanaPage = deviceProductModeService.getProductModePage(id);
        return LanaResult.ok(lanaPage);
    }



    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    public LanaResult saveProductModePage(@RequestBody @Valid DeviceProductModeSave saveVO) {
        deviceProductModeService.saveDeviceGroup(saveVO);
        return LanaResult.ok();
    }


}
