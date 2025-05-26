package com.lana.abutment.mqtthandle.entity.vo.push;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/8/30 13:14
 */
@Data
@Schema(description = "发送消息实体")
public class DirectivesSend {

    @Schema(description = "发送主题")
    private String topic;

     @Schema(description = "发送数据")
    private String pushData;

}
