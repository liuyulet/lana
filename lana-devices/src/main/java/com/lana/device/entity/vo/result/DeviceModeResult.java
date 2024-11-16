package com.lana.device.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@Schema(description = "设备物模型")
public class DeviceModeResult {

    @Schema(description = "id")
    private Long id;
    /**
     * 属性名称
     */
    @Schema(description = "属性名称")
    private String modeName;
    /**
     * 属性标志
     */
    @Schema(description = "属性标志")
    private String modeSigns;

    /**
     * 数据类型
     */
    @Schema(description = "数据类型")
    private String modeDataType;

    /**
     * 数据来源
     */
    @Schema(description = "数据来源")
    private Integer modeDataSource;

    /**
     * 控制属性  0：否   1：是
     */
    @Schema(description = "控制属性  0：否   1：是")
    private Integer modeControlAtt;
    /**
     * 补充说明
     */
    @Schema(description = "补充说明")
    private String modeRemark;
    /**
     * 产品实例id
     */
    @Schema(description = "产品实例id")
    private Long deviceItemId;
    /**
     * 默认值
     */
    @Schema(description = "默认值")
    private Long defultValue;

}
