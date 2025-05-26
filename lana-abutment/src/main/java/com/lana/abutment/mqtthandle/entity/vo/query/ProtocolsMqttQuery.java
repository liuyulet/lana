package com.lana.abutment.mqtthandle.entity.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/8/20 18:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "协议名称")
public class ProtocolsMqttQuery {

    /**
     * 协议名称
     */
    @Schema(description = "协议名称")
    private String name;

}
