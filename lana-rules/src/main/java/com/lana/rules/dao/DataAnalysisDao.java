package com.lana.rules.dao;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.lana.rules.entity.DeviceControlEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:34
 */
@Mapper
public interface DataAnalysisDao {
    @DS("tdengine")
    void save( @Param("sql")String sql);

    List<DeviceControlEntity> getDeviceControlList(@Param("longs") List<BigInteger> longs);
}
