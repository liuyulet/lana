package com.lana.device.service;

import com.lana.base.mybatis.service.BaseService;
import com.lana.device.entity.DeviceProductModeEntity;
import com.lana.device.entity.vo.result.DeviceProductModeResult;
import com.lana.device.entity.vo.save.DeviceProductModeSave;

import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:33
 */
public interface DeviceProductModeService extends BaseService<DeviceProductModeEntity> {

    List<DeviceProductModeResult> getProductModePage(Long id);

    void saveDeviceGroup(DeviceProductModeSave saveVO);
}
