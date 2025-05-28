package com.lana.device.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.device.entity.vo.query.DeviceProductTypeQuery;
import com.lana.device.entity.vo.result.DeviceAbutmentResult;
import com.lana.device.entity.vo.result.DeviceProductTypeResult;
import com.lana.device.entity.vo.save.DeviceProductTypeSave;
import com.lana.device.entity.vo.save.SaveDeviceAbutmentSave;
import com.lana.device.entity.vo.update.DeviceProductTypeUpdate;
import com.lana.device.service.DeviceProductTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/30 14:18
 */
@RestController
@RequestMapping("/devices/deviceProductType")
@Tag(name = "产品分类")
@ApiSupport(author = "liuyulet")
public class DeviceProductTypeController {

    @Resource
    private DeviceProductTypeService deviceProductTypeService;
    @GetMapping("/list")
    @Operation(summary = "列表")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('devices:deviceProductType:list')")
    public LanaResult<List<DeviceProductTypeResult>> list(@ParameterObject @Valid DeviceProductTypeQuery query) {
        List<DeviceProductTypeResult> list = deviceProductTypeService.getList(query);

        return LanaResult.ok(list);
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('devices:deviceProductType:save')")
    public LanaResult<String> save(@RequestBody @Valid DeviceProductTypeSave saveVO) {
        deviceProductTypeService.saveDeviceProductType(saveVO);
        return LanaResult.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('devices:deviceProductType:update')")
    public LanaResult<String> update(@RequestBody @Valid DeviceProductTypeUpdate updateVO) {
        deviceProductTypeService.updateDeviceProductType(updateVO);

        return LanaResult.ok();
    }

    @GetMapping("/delete")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('devices:deviceProductType:delete')")
    public LanaResult<String> delete(@RequestParam("id") Long id) {
        deviceProductTypeService.deleteDeviceProductType(id);

        return LanaResult.ok();
    }

    /**
     * 产品接入管理配置
     */
    @GetMapping("/getDeviceProtocolsMode")
    @Operation(summary = "查询所属协议")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<DeviceAbutmentResult> getDeviceProtocolsMode(@RequestParam("id") Long id) {
        DeviceAbutmentResult data = deviceProductTypeService.getDeviceProtocolsMode(id);
        return LanaResult.ok(data);
    }

    @PostMapping("/saveDeviceProtocolsMode")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    public LanaResult saveDeviceProtocolsMode(@RequestBody @Valid SaveDeviceAbutmentSave saveVO) {
        //作为产品与接入配置的关联关系表，先增在删
        deviceProductTypeService.saveDeviceProtocolsMode(saveVO);
        return LanaResult.ok();
    }

}
