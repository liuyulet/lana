package com.lana.device.service.impl;

import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.device.dao.DeviceProductModeDao;
import com.lana.device.entity.DeviceProductModeEntity;
import com.lana.device.entity.vo.result.DeviceProductModeResult;
import com.lana.device.entity.vo.save.DeviceProductModeSave;
import com.lana.device.service.DeviceProductModeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:33
 */
@Slf4j
@Service
public class DeviceProductModeServiceImpl extends BaseServiceImpl<DeviceProductModeDao, DeviceProductModeEntity> implements DeviceProductModeService {


    @Override
    public List<DeviceProductModeResult> getProductModePage(Long id) {
        return baseMapper.getProductModePage(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveDeviceGroup(DeviceProductModeSave saveVO) {
        if(saveVO.getDeviceProductModeListSave().size()>0){
            //todo 后续再优化吧
            //删除
            baseMapper.deleteDeviceGroup(saveVO.getProductTypeId());
            baseMapper.saveDeviceGroup(saveVO);
        }else {
            baseMapper.deleteDeviceGroup(saveVO.getProductTypeId());
        }

    }
}
