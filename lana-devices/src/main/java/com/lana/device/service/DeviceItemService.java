package com.lana.device.service;

import com.lana.base.mybatis.service.BaseService;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.device.entity.DeviceItemEntity;
import com.lana.device.entity.vo.query.DeviceItemQuery;
import com.lana.device.entity.vo.query.GroupDeviceItemQuery;
import com.lana.device.entity.vo.result.GroupDeviceItemResult;
import com.lana.device.entity.vo.result.DeviceItemResult;
import com.lana.device.entity.vo.save.DeviceItemSave;
import com.lana.device.entity.vo.update.DeviceItemUpdate;

import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:33
 */
public interface DeviceItemService extends BaseService<DeviceItemEntity> {

    LanaPage<DeviceItemResult> getDeviceItemPage(DeviceItemQuery query);

    void saveDeviceItem(DeviceItemSave vo);

    void updateByUserId(DeviceItemUpdate vo);

    void deleteDeviceItem(List<Long> idList);

    LanaPage<GroupDeviceItemResult> groupDeviceItemPage(GroupDeviceItemQuery query);

    List<GroupDeviceItemResult> groupDeviceItemList(Long groupId);

    long getByDeviceType(Long id);
}
