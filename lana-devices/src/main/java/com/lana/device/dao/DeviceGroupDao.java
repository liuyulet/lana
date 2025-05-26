package com.lana.device.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.mybatis.dao.BaseDao;
import com.lana.device.entity.DeviceGroupEntity;
import com.lana.device.entity.DeviceItemEntity;
import com.lana.device.entity.vo.query.DeviceGroupQuery;
import com.lana.device.entity.vo.query.DeviceItemQuery;
import com.lana.device.entity.vo.result.DeviceGroupListResult;
import com.lana.device.entity.vo.result.DeviceGroupResult;
import com.lana.device.entity.vo.result.DeviceItemGetResult;
import com.lana.device.entity.vo.save.SaveGroupDevice;
import com.lana.device.entity.vo.update.GroupDevicedate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:34
 */
@Mapper
public interface DeviceGroupDao extends BaseDao<DeviceGroupEntity> {

    IPage<DeviceGroupResult> getDeviceGroupPage(@Param("page")IPage<DeviceGroupEntity> page,@Param("model") DeviceGroupQuery query,@Param("DataScopeIgnore") boolean b);

    void saveBindingDevice(@Param("saveVO") SaveGroupDevice saveVO);

    void deleteBindingDevice(@Param("updateVo") GroupDevicedate updateVo);

    List<DeviceGroupListResult> getDeviceGroupList();
}
