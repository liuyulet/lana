package com.lana.device.dao;

import com.lana.base.mybatis.dao.BaseDao;
import com.lana.device.entity.DeviceAbutmentEntity;
import com.lana.device.entity.DeviceProductTypeEntity;
import com.lana.device.entity.vo.query.DeviceProductTypeQuery;
import com.lana.device.entity.vo.result.DeviceAbutmentResult;
import com.lana.device.entity.vo.result.DeviceProductTypeResult;
import com.lana.device.entity.vo.result.ProtocolResult;
import com.lana.device.entity.vo.save.SaveDeviceAbutmentSave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:34
 */
@Mapper
public interface DeviceProductTypeDao extends BaseDao<DeviceProductTypeEntity> {

    List<DeviceProductTypeResult> getList(@Param("params") DeviceProductTypeQuery query,@Param("DataScopeIgnore") boolean DataScopeIgnore);

    DeviceAbutmentEntity getProtocolsId(Long id);

    List<ProtocolResult> getDeviceProtocolsMode();

    void delDeviceProtocolsMode(@Param("productTypeId") Long productTypeId);

    void saveDeviceProtocolsMode(@Param("saveVO") SaveDeviceAbutmentSave saveVO);
}
