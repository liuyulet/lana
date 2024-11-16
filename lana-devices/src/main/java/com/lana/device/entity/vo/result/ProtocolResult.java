package com.lana.device.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/8/20 18:36
 */
@Data
@Schema(description = "mqtt协议信息")
public class ProtocolResult {

    @Schema(description = "id")
    private Long value;

    @Schema(description = "mqtt协议名称")
    private String label;

}
