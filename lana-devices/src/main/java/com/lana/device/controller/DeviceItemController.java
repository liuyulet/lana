package com.lana.device.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.device.entity.vo.query.DeviceItemQuery;
import com.lana.device.entity.vo.result.DeviceItemResult;
import com.lana.device.entity.vo.save.DeviceItemSave;
import com.lana.device.entity.vo.update.DeviceItemUpdate;
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
@RequestMapping("/devices/deviceItem")
@Tag(name = "设备管理")
@ApiSupport(author = "liuyulet")
public class DeviceItemController {
    @Resource
    private DeviceItemService deviceItemService;

    /**
     * 数据分页查询
     * @param query
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@PreAuthorize("hasAuthority('devices:deviceItem:page')")
    public LanaResult<LanaPage<DeviceItemResult>> getDeviceItemPage(@ParameterObject @Valid DeviceItemQuery query) {

        LanaPage<DeviceItemResult> lanaPage = deviceItemService.getDeviceItemPage(query);
        return LanaResult.ok(lanaPage);
    }


    // todo 项目启动的时候，需要将物模型的数据初始化在redis中

    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('devices:deviceItem:save')")
    public LanaResult saveDeviceItem(@RequestBody @Valid DeviceItemSave vo) {
        deviceItemService.saveDeviceItem(vo);
        return LanaResult.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('devices:deviceItem:update')")
    public LanaResult update(@RequestBody @Valid DeviceItemUpdate vo) {
        deviceItemService.updateByUserId(vo);
        return LanaResult.ok();
    }


    @PostMapping("/delete")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('devices:deviceItem:delete')")
    public LanaResult<String> delete(@RequestBody List<Long> idList) {
        deviceItemService.deleteDeviceItem(idList);
        return LanaResult.ok();
    }
}
