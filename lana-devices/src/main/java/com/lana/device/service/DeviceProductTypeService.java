package com.lana.device.service;

import com.lana.base.mybatis.service.BaseService;
import com.lana.device.entity.DeviceProductTypeEntity;
import com.lana.device.entity.vo.query.DeviceProductTypeQuery;
import com.lana.device.entity.vo.result.DeviceAbutmentResult;
import com.lana.device.entity.vo.result.DeviceProductTypeResult;
import com.lana.device.entity.vo.save.DeviceProductTypeSave;
import com.lana.device.entity.vo.save.SaveDeviceAbutmentSave;
import com.lana.device.entity.vo.update.DeviceProductTypeUpdate;

import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:33
 */
public interface DeviceProductTypeService extends BaseService<DeviceProductTypeEntity> {

    List<DeviceProductTypeResult> getList(DeviceProductTypeQuery query);

    void saveDeviceProductType(DeviceProductTypeSave saveVO);

    void updateDeviceProductType(DeviceProductTypeUpdate updateVO);

    void deleteDeviceProductType(Long id);

    DeviceAbutmentResult getDeviceProtocolsMode(Long id);

    void saveDeviceProtocolsMode(SaveDeviceAbutmentSave saveVO);
}
