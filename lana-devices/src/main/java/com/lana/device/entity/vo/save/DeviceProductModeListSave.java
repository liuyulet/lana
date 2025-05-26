package com.lana.device.entity.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@Schema(description = "产品物模型list对象，作为存储list实体使用")
public class DeviceProductModeListSave {


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
     * 补充说明
     */
    @Schema(description = "补充说明")
    private String modeRemark;
}
