package com.lana.device.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@Schema(description = "设备功能")
public class DeviceControlDataResult {


    @Schema(description = "id", required = true)
    @NotNull(message = "id不能为空")
     private Long id;
     /**
     * 设备id
     */
     @Schema(description = "设备id")
     private Long deviceItemId;
     /**
     * 功能类型
     */
     @Schema(description = "功能类型")
     private Integer controlType;
     /**
     * 控制类型名称
     */
     @Schema(description = "控制类型名称")
     private String controlTypeLabel;
     /**
     * 功能名称
     */
     @Schema(description = "功能名称")
     private String controlName;
     /**
     * 属性标志
     */
     @Schema(description = "属性标志")
     private String controlMode;
     /**
     * 数据类型
     */
     @Schema(description = "数据类型")
     private String controlDataType;
     /**
     * 属性类型
     */
     @Schema(description = "属性类型名称")
     private String controlDataTypeLabel;
     /**
     * 属性值
     */
     @Schema(description = "属性值")
     private String controlDefultValue;

}
