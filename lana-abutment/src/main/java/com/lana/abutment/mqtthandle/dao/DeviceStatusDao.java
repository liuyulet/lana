package com.lana.abutment.mqtthandle.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:34
 */
@Mapper
public interface DeviceStatusDao {

    void updataStatus(@Param("clientId") Long clientId,@Param("status") int status);
}
