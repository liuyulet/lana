package com.lana.abutment.mqtthandle.dao;


import com.lana.abutment.mqtthandle.entity.vo.query.ProtocolsMqttQuery;
import com.lana.abutment.mqtthandle.entity.vo.result.ProtocolsMqttResult;
import com.lana.abutment.mqtthandle.entity.ProtocolsMqttEntity;
import com.lana.abutment.mqtthandle.entity.vo.result.RuleProtocolsMqttResult;
import com.lana.abutment.mqtthandle.initializer.cacheproties.DeviceItemModeCacheSave;
import com.lana.base.mybatis.dao.BaseDao;
import com.lana.abutment.mqtthandle.entity.InintProtocolsMqttEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:34
 */
@Mapper
public interface ProtocolsMqttDao extends BaseDao<ProtocolsMqttEntity> {


    List<ProtocolsMqttResult> getMqttGroupPage(@Param("model") ProtocolsMqttQuery query);

    void updateByProtocol(@Param("protocolsMqttUpdatate")InintProtocolsMqttEntity protocolsMqttUpdatate);

    void insertProtocol(@Param("protocolsMqttUpdatate")InintProtocolsMqttEntity protocolsMqttUpdatate);

    Long getProtocolsId(Long id);

    List<DeviceItemModeCacheSave> getDevicveeModeList();

    List<RuleProtocolsMqttResult> getMqttGroupList(@Param("model") ProtocolsMqttQuery query);


/*    @Slave
    void initializeTdengine(@Param("sql") String sql);*/

/*    @DS("tdengine")
    String getTdengineTable(@Param("airdetectionName") String airdetectionName);*/
}
