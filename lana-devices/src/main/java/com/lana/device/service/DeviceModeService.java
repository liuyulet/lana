package com.lana.device.service;

import com.lana.base.mybatis.service.BaseService;
import com.lana.device.entity.DeviceModeEntity;
import com.lana.device.entity.vo.result.DeviceModeResult;
import com.lana.device.entity.vo.result.DeviceProductModeResult;
import com.lana.device.entity.vo.save.DeviceItemModeSave;
import com.lana.device.entity.vo.update.DeviceModeUpdate;

import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:33
 */
public interface DeviceModeService extends BaseService<DeviceModeEntity> {

    //继承自产品的物模型
    void saveDeviceMode(List<DeviceProductModeResult> productModeList, Long id);
    //新增物模型
    void saveDeviceModes(DeviceItemModeSave saveVO);
    List<DeviceModeResult> getDeviceItemModeList(Long id);

    List<DeviceModeResult> getDeviceContoleList(Long id);

    void saveContoleModel(DeviceModeUpdate updataVO);
}
