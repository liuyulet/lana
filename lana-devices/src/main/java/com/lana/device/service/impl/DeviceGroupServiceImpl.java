package com.lana.device.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.device.convert.DeviceGroupConvert;
import com.lana.device.dao.DeviceGroupDao;
import com.lana.device.entity.DeviceGroupEntity;

import com.lana.device.entity.vo.query.DeviceGroupQuery;
import com.lana.device.entity.vo.result.DeviceGroupListResult;
import com.lana.device.entity.vo.result.DeviceGroupResult;
import com.lana.device.entity.vo.save.DeviceGroupSave;
import com.lana.device.entity.vo.save.SaveGroupDevice;
import com.lana.device.entity.vo.update.DeviceGroupUpdate;
import com.lana.device.entity.vo.update.GroupDevicedate;
import com.lana.device.service.DeviceGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:33
 */
@Slf4j
@Service
public class DeviceGroupServiceImpl extends BaseServiceImpl<DeviceGroupDao, DeviceGroupEntity> implements DeviceGroupService {

    @Override
    public LanaPage<DeviceGroupResult> getDeviceGroupPage(DeviceGroupQuery query) {
        IPage<DeviceGroupResult> page = baseMapper.getDeviceGroupPage(getPage(query), query,true);
        return new LanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

    @Override
    public void saveDeviceGroup(DeviceGroupSave saveVO) {
        DeviceGroupEntity entity = DeviceGroupConvert.INSTANCE.convert(saveVO);
        baseMapper.insert(entity);
    }

    @Override
    public void updateDeviceGroup(DeviceGroupUpdate updateVo) {
        DeviceGroupEntity entity = DeviceGroupConvert.INSTANCE.convert(updateVo);
        baseMapper.updateById(entity);
    }

    @Override
    public void deleteDeviceGroup(List<Long> idList) {
        // todo 先检查是否有绑定的设备，之后再进行删除
        baseMapper.deleteBatchIds(idList);

    }

    @Override
    public void saveBindingDevice(SaveGroupDevice saveVO) {
        baseMapper.saveBindingDevice(saveVO);
    }

    @Override
    public void deleteBindingDevice(GroupDevicedate updateVo) {
        baseMapper.deleteBindingDevice(updateVo);
    }

    @Override
    public List<DeviceGroupListResult> getDeviceGroupList() {
        return baseMapper.getDeviceGroupList();
    }
}
