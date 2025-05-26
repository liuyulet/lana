package com.lana.abutment.mqtthandle.convert;

import com.lana.abutment.mqtthandle.entity.ProtocolsMqttEntity;
import com.lana.abutment.mqtthandle.entity.vo.update.ProtocolsMqttUpdate;
import com.lana.abutment.mqtthandle.entity.vo.save.ProtocolsMqttSave;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/8/30 13:20
 */
@Mapper
public interface ProtocolsMqttConvert {
    ProtocolsMqttConvert INSTANCE = Mappers.getMapper(ProtocolsMqttConvert.class);

    ProtocolsMqttEntity convert(ProtocolsMqttSave saveVO);
    ProtocolsMqttEntity convert(ProtocolsMqttUpdate saveVO);

}
