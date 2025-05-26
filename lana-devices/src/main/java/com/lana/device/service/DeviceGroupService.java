package com.lana.device.service;

import com.lana.base.mybatis.service.BaseService;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.device.entity.DeviceGroupEntity;
import com.lana.device.entity.vo.query.DeviceGroupQuery;
import com.lana.device.entity.vo.result.DeviceGroupListResult;
import com.lana.device.entity.vo.result.DeviceGroupResult;
import com.lana.device.entity.vo.save.DeviceGroupSave;
import com.lana.device.entity.vo.save.SaveGroupDevice;
import com.lana.device.entity.vo.update.DeviceGroupUpdate;
import com.lana.device.entity.vo.update.GroupDevicedate;

import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:33
 */
public interface DeviceGroupService extends BaseService<DeviceGroupEntity> {
    LanaPage<DeviceGroupResult> getDeviceGroupPage(DeviceGroupQuery query);

    void saveDeviceGroup(DeviceGroupSave saveVO);

    void updateDeviceGroup(DeviceGroupUpdate updateVo);

    void deleteDeviceGroup(List<Long> idList);

    void saveBindingDevice(SaveGroupDevice saveVO);

    void deleteBindingDevice(GroupDevicedate updateVo);

    List<DeviceGroupListResult> getDeviceGroupList();
}
