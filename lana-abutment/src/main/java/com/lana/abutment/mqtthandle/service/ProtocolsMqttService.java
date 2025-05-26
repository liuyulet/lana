package com.lana.abutment.mqtthandle.service;

import com.lana.abutment.mqtthandle.entity.vo.query.ProtocolsMqttQuery;
import com.lana.abutment.mqtthandle.entity.vo.result.ProtocolsMqttResult;
import com.lana.abutment.mqtthandle.entity.vo.result.RuleProtocolsMqttResult;
import com.lana.abutment.mqtthandle.entity.vo.update.ProtocolsMqttUpdate;
import com.lana.abutment.mqtthandle.initializer.proties.ProtocolsMqttProperties;
import com.lana.base.mybatis.service.BaseService;
import com.lana.abutment.mqtthandle.entity.ProtocolsMqttEntity;
import com.lana.abutment.mqtthandle.entity.vo.save.ProtocolsMqttSave;

import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/8/30 11:16
 */
public interface ProtocolsMqttService extends BaseService<ProtocolsMqttEntity> {
    List<ProtocolsMqttResult> getMqttGroupPage(ProtocolsMqttQuery query);

    void saveProtocolsMqttSave(ProtocolsMqttSave saveVO);

    void updateProtocolsMqtt(ProtocolsMqttUpdate updateVo);

    void deleteProtocolsMqtt(Long id);

    void initializeMqtt(ProtocolsMqttProperties protocolsMqttProperties);

    void initializeCache();

    List<RuleProtocolsMqttResult> getMqttGroupList(ProtocolsMqttQuery query);


    //void initializeTdengine(ProtocolsMqttProperties protocolsMqttProperties);
}
