package com.lana.device.service;

import com.lana.base.mybatis.service.BaseService;
import com.lana.device.entity.DeviceControlDataEntity;
import com.lana.device.entity.vo.query.DeviceControlDataQuery;
import com.lana.device.entity.vo.result.DeviceControlDataResult;
import com.lana.device.entity.vo.save.DeviceControlDataSave;
import com.lana.device.entity.vo.update.DeviceControlDataUpdate;

import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:33
 */
public interface DeviceControlDataService extends BaseService<DeviceControlDataEntity> {


    DeviceControlDataEntity saveDeviceControl(DeviceControlDataSave saveVO);

    void updateDeviceControlData(DeviceControlDataUpdate updateVO);

    void deleteDeviceControlData(Long id);

    List<DeviceControlDataResult> getDeviceControlDataList(DeviceControlDataQuery query);

}
