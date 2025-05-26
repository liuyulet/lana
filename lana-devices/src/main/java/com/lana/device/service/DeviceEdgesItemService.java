package com.lana.device.service;

import com.lana.base.mybatis.service.BaseService;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.device.entity.DeviceEdgesItemEntity;
import com.lana.device.entity.vo.query.DeviceEdgesQuery;
import com.lana.device.entity.vo.result.DeviceEdgesItemResult;
import com.lana.device.entity.vo.result.EdgesItemDataResult;
import com.lana.device.entity.vo.save.DeviceEdgesItemSave;
import com.lana.device.entity.vo.save.DeviceItemSave;
import com.lana.device.entity.vo.update.DeviceEdgesItemUpdate;

import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/10/25 16:45
 */
public interface DeviceEdgesItemService extends BaseService<DeviceEdgesItemEntity> {

    LanaPage<DeviceEdgesItemResult> getDeviceEdgesItemPage(DeviceEdgesQuery deviceEdgesQuery);
    DeviceEdgesItemEntity saveDeviceEdgesItem(DeviceEdgesItemSave saveVo);

    DeviceEdgesItemEntity updateDeviceEdgesItem(DeviceEdgesItemUpdate updateVo);

    DeviceEdgesItemResult getEdgesItemByProductTypeId(Long id);

    List<EdgesItemDataResult> getEdgesItemEdge(DeviceEdgesItemResult deviceEdgesItemResult);

    void delEdgesItem(String productTypeId);


}
