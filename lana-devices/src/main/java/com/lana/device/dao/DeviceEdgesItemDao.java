package com.lana.device.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.mybatis.dao.BaseDao;
import com.lana.device.entity.DeviceEdgesItemEntity;
import com.lana.device.entity.vo.query.DeviceEdgesQuery;
import com.lana.device.entity.vo.result.DeviceEdgesItemResult;
import com.lana.device.entity.vo.result.EdgesItemDataResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/10/25 16:56
 */
@Mapper
public interface DeviceEdgesItemDao extends BaseDao<DeviceEdgesItemEntity> {

    List<EdgesItemDataResult> getEdgesItemEdge();

    void delEdgesItem(@Param("productTypeId") String productTypeId);

    IPage<DeviceEdgesItemResult> getDeviceEdgesItemPage(@Param("page")IPage<DeviceEdgesItemEntity> page,@Param("model") DeviceEdgesQuery deviceEdgesQuery,@Param("DataScopeIgnore") boolean b);
}
