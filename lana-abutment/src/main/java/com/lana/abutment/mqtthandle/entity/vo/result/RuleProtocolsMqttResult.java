package com.lana.abutment.mqtthandle.entity.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/8/20 18:36
 */
@Data
@Schema(description = "情景模式mqtt协议")
public class RuleProtocolsMqttResult {

    @Schema(description = "id")
    private Long id;
    /**
     * mqtt名称
     */
    private String label;

}
