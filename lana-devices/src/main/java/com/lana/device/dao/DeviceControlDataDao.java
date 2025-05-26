package com.lana.device.dao;

import com.lana.base.mybatis.dao.BaseDao;
import com.lana.device.entity.DeviceControlDataEntity;
import com.lana.device.entity.vo.query.DeviceControlDataQuery;
import com.lana.device.entity.vo.result.DeviceControlDataResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/10/29 18:34
 */
@Mapper
public interface DeviceControlDataDao extends BaseDao<DeviceControlDataEntity> {


    List<DeviceControlDataResult> getDeviceControlDataList(@Param("query") DeviceControlDataQuery query);

}
