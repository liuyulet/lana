package com.lana.device.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.mybatis.dao.BaseDao;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.device.entity.DeviceItemEntity;
import com.lana.device.entity.vo.query.DeviceItemQuery;
import com.lana.device.entity.vo.query.GroupDeviceItemQuery;
import com.lana.device.entity.vo.result.DeviceItemGetResult;
import com.lana.device.entity.vo.result.DeviceItemResult;
import com.lana.device.entity.vo.result.GroupDeviceItemResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:34
 */
@Mapper
public interface DeviceItemDao extends BaseDao<DeviceItemEntity> {

    IPage<DeviceItemGetResult> getDeviceItemPage(@Param("page") IPage<DeviceItemEntity> page, @Param("query") DeviceItemQuery query, @Param("DataScopeIgnore") boolean DataScopeIgnore);

    void insertDeviceItemProduct(@Param("productId") List<Long> productTypeShow,@Param("itemid") Long id);

    void deleteDeviceItemProduct(@Param("itemid") Long id);

    IPage<GroupDeviceItemResult> groupDeviceItemPage(@Param("page") IPage<DeviceItemEntity> page, @Param("query") GroupDeviceItemQuery query, @Param("DataScopeIgnore") boolean b);

    List<GroupDeviceItemResult> groupDeviceItemList(@Param("groupId") Long groupId);

    @DS("tdengine")
    void createTdTable(String sql);

    long getByDeviceType(@Param("id") Long id);
}
