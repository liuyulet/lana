package com.lana.device.dao;

import com.lana.base.mybatis.dao.BaseDao;
import com.lana.device.entity.DeviceProductModeEntity;
import com.lana.device.entity.vo.result.DeviceProductModeResult;
import com.lana.device.entity.vo.save.DeviceProductModeSave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:34
 */
@Mapper
public interface DeviceProductModeDao extends BaseDao<DeviceProductModeEntity> {

    List<DeviceProductModeResult> getProductModePage(Long id);

    void saveDeviceGroup(@Param("saveVO") DeviceProductModeSave saveVO);

    void deleteDeviceGroup(@Param("productTypeId") Long productTypeId);
}
