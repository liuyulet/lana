package com.lana.device.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.device.entity.DeviceEdgesItemEntity;
import com.lana.device.entity.vo.query.DeviceEdgesQuery;
import com.lana.device.entity.vo.query.DeviceItemQuery;
import com.lana.device.entity.vo.result.DeviceEdgesItemResult;
import com.lana.device.entity.vo.result.DeviceItemResult;
import com.lana.device.entity.vo.save.DeviceEdgesItemSave;
import com.lana.device.entity.vo.save.DeviceItemSave;
import com.lana.device.entity.vo.update.DeviceEdgesItemUpdate;
import com.lana.device.entity.vo.update.DeviceItemUpdate;
import com.lana.device.service.DeviceEdgesItemService;
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
@RequestMapping("/devices/edgesItem")
@Tag(name = "边缘计算管理")
@ApiSupport(author = "liuyulet")
public class DeviceEdgesItemController {

    @Resource
    private DeviceEdgesItemService deviceEdgesItemService;


    /**
     * 数据分页查询
     * @param
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "查询边缘实例数据")
    @OptLog(type = OperateTypeEnum.QUERY)
    public LanaResult<LanaPage<DeviceEdgesItemResult>> getDeviceEdgesItemPage(@ParameterObject @Valid DeviceEdgesQuery deviceEdgesQuery) {
        LanaPage<DeviceEdgesItemResult> DeviceEdgesItemResultPage = deviceEdgesItemService.getDeviceEdgesItemPage(deviceEdgesQuery);
        return LanaResult.ok(DeviceEdgesItemResultPage);
    }


    /**
     * 保存边缘实例
     * 注意，设备产品详情中也使用了该接口
     * @param
     * @return
     */
    @PostMapping("/save")
    @Operation(summary = "保存边缘实例")
    @OptLog(type = OperateTypeEnum.INSERT)
    public LanaResult<DeviceEdgesItemEntity> saveDeviceEdgesItem(@RequestBody @Valid DeviceEdgesItemSave saveVo) {
        return LanaResult.ok(deviceEdgesItemService.saveDeviceEdgesItem(saveVo));
    }


    /**
     * 修改边缘实例
     * 注意，设备产品详情中也使用了该接口
     * @param
     * @return
     */
    @PostMapping("/update")
    @Operation(summary = "修改边缘实例")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public LanaResult<DeviceEdgesItemEntity> updateDeviceEdgesItem(@RequestBody @Valid DeviceEdgesItemUpdate updateVo) {
        return LanaResult.ok(deviceEdgesItemService.updateDeviceEdgesItem(updateVo));
    }


}
