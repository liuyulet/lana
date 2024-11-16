package com.lana.device.service.impl;

import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.CacheOps;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.base.operatelog.enums.GeneralPrefixEnum;
import com.lana.device.entity.DeviceModeEntity;
import com.lana.device.dao.DeviceModeDao;
import com.lana.device.entity.vo.result.DeviceModeResult;
import com.lana.device.entity.vo.result.DeviceProductModeResult;
import com.lana.device.entity.vo.save.DeviceItemModeSave;
import com.lana.device.entity.vo.save.DeviceModeListSave;
import com.lana.device.entity.vo.update.DeviceModeUpdate;
import com.lana.device.service.DeviceModeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:33
 */
@Slf4j
@Service
public class DeviceModeServiceImpl extends BaseServiceImpl<DeviceModeDao, DeviceModeEntity> implements DeviceModeService {

    @Resource
    private CacheOps cacheOps;
    @Override
    public void saveDeviceMode(List<DeviceProductModeResult> productModeList, Long id) {
        baseMapper.saveDeviceMode(productModeList,id);
    }

    @Override
    public void saveDeviceModes(DeviceItemModeSave saveVO) {
        if(saveVO.getDeviceModeListSave().size()>0){
            //删除
            baseMapper.deleteDeviceMode(saveVO.getDeviceItemId());
            baseMapper.saveDeviceModes(saveVO);
            //检索是否有这个表
            //删除td子表数据
            baseMapper.deltTable("DROP TABLE "+GeneralPrefixEnum.TABLE_PREFIX.getValue()+saveVO.getDeviceItemId()+";");
            //创建td子表
            String sql = assembleTheSQL(saveVO.getDeviceModeListSave(),saveVO.getDeviceItemId());
            baseMapper.createTdTable(sql);
            String key = CacheKeyBuilder.deviceMode(GeneralPrefixEnum.TABLE_PREFIX.getValue()+saveVO.getDeviceItemId());
            Object data = cacheOps.get(key);
            //物模型加入缓存中
            ArrayList<String> objects = new ArrayList<>();
            for (DeviceModeListSave deviceModeListSave:saveVO.getDeviceModeListSave()) {
                objects.add(deviceModeListSave.getModeSigns());
            }
            if(data!=null){
                cacheOps.delete(key);
                cacheOps.set(key,objects);
            }else {
                cacheOps.set(key,objects);
            }


        }else {
            baseMapper.deleteDeviceMode(saveVO.getDeviceItemId());
        }
    }

    /**
     * 组装生成sql
     * @param deviceModeListSave
     * @return
     */
    private String assembleTheSQL(List<DeviceModeListSave> deviceModeListSave, long deviceId) {
        //组装普通表sql
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(GeneralPrefixEnum.TABLE_PREFIX.getValue());
        sb.append(deviceId);
        sb.append("(ts timestamp,");
        for (int i = 0; i < deviceModeListSave.size(); i++) {
            sb.append(deviceModeListSave.get(i).getModeSigns());
            sb.append(" ");
            sb.append(deviceModeListSave.get(i).getModeDataType());
            if (i < deviceModeListSave.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(");");
        return sb.toString();
    };


    @Override
    public List<DeviceModeResult> getDeviceItemModeList(Long id) {
        return baseMapper.getDeviceItemModeList(id);
    }

    @Override
    public List<DeviceModeResult> getDeviceContoleList(Long id) {
        return baseMapper.getDeviceContoleList(id);
    }

    @Override
    public void saveContoleModel(DeviceModeUpdate updataVO) {
        baseMapper.saveContoleModel(updataVO);
    }
}
