package com.lana.device.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.device.entity.vo.query.DeviceGroupQuery;
import com.lana.device.entity.vo.query.DeviceItemQuery;
import com.lana.device.entity.vo.query.GroupDeviceItemQuery;
import com.lana.device.entity.vo.result.DeviceGroupListResult;
import com.lana.device.entity.vo.result.DeviceGroupResult;
import com.lana.device.entity.vo.result.DeviceItemResult;
import com.lana.device.entity.vo.result.GroupDeviceItemResult;
import com.lana.device.entity.vo.save.DeviceGroupSave;
import com.lana.device.entity.vo.save.SaveGroupDevice;
import com.lana.device.entity.vo.update.DeviceGroupUpdate;
import com.lana.device.entity.vo.update.GroupDevicedate;
import com.lana.device.service.DeviceGroupService;
import com.lana.device.service.DeviceItemService;
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
@RequestMapping("/devices/deviceGroup")
@Tag(name = "设备分组")
@ApiSupport(author = "liuyulet")
public class DeviceGroupController {
    @Resource
    private DeviceGroupService deviceGroupService;
    @Resource
    private DeviceItemService deviceItemService;


    @GetMapping("/page")
    @Operation(summary = "分页查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('devices:deviceGroup:page')")
    public LanaResult<LanaPage<DeviceGroupResult>> getDeviceGroupPage(@ParameterObject @Valid DeviceGroupQuery query) {
        LanaPage<DeviceGroupResult> lanaPage = deviceGroupService.getDeviceGroupPage(query);
        return LanaResult.ok(lanaPage);
    }


    @GetMapping("/getDeviceGroupList")
    @Operation(summary = "分组列表查询，规则编排使用")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<List<DeviceGroupListResult>> getDeviceGroupList() {
        List<DeviceGroupListResult> deviceGroupList = deviceGroupService.getDeviceGroupList();
        return LanaResult.ok(deviceGroupList);
    }

    /**
     * 用户数据分页查询
     * @param query
     * @return
     */
    @GetMapping("/groupDeviceItemPage")
    @Operation(summary = "所属分组设备分页查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('devices:deviceItem:page')")
    public LanaResult<LanaPage<GroupDeviceItemResult>> groupDeviceItemPage(@ParameterObject @Valid GroupDeviceItemQuery query) {
        LanaPage<GroupDeviceItemResult> lanaPage = deviceItemService.groupDeviceItemPage(query);
        return LanaResult.ok(lanaPage);
    }

    @GetMapping("/groupDeviceItemList")
    @Operation(summary = "所属分组分页查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('devices:deviceItem:page')")
    public LanaResult<List<GroupDeviceItemResult>> groupDeviceItemList(@RequestParam   Long groupId ) {
        List<GroupDeviceItemResult> List = deviceItemService.groupDeviceItemList(groupId);
        return LanaResult.ok(List);
    }

    @PostMapping("/saveBindingDevice")
    @Operation(summary = "保存绑定的设备")
    @OptLog(type = OperateTypeEnum.UPDATE)
    //@PreAuthorize("hasAuthority('devices:deviceGroup:saveBinding')")
    public LanaResult<String> saveBindingDevice(@RequestBody @Valid SaveGroupDevice saveVO) {
        deviceGroupService.saveBindingDevice(saveVO);
        return LanaResult.ok();
    }

    @PostMapping("/deleteBindingDevice")
    @Operation(summary = "解除绑定的设备")
    @OptLog(type = OperateTypeEnum.UPDATE)
    //@PreAuthorize("hasAuthority('devices:deviceGroup:saveBinding')")
    public LanaResult<String> deleteBindingDevice(@RequestBody @Valid GroupDevicedate updateVo) {
        deviceGroupService.deleteBindingDevice(updateVo);
        return LanaResult.ok();
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('devices:deviceGroup:save')")
    public LanaResult saveDeviceGroup(@RequestBody @Valid DeviceGroupSave saveVO) {
        deviceGroupService.saveDeviceGroup(saveVO);
        return LanaResult.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('devices:deviceGroup:update')")
    public LanaResult<String> updateDeviceGroup(@RequestBody @Valid DeviceGroupUpdate updateVo) {
        deviceGroupService.updateDeviceGroup(updateVo);
        return LanaResult.ok();
    }

    @GetMapping("/delete")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('devices:deviceGroup:delete')")
    public LanaResult<String> deleteDeviceGroup(@RequestParam("id") List<Long> idList) {
        deviceGroupService.deleteDeviceGroup(idList);
        return LanaResult.ok();
    }

}
