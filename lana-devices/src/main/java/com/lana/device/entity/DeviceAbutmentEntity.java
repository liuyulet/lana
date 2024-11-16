package com.lana.device.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/8/30 13:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("device_abutment")
public class DeviceAbutmentEntity {

    @Schema(description = "现绑定的协议ID")
    private Long id;

    @Schema(description = "产品id")
    @NotNull(message = "产品id不能为空")
    private Long productTypeId;

    @Schema(description = "协议id")
    @NotNull(message = "协议id不能为空")
    private Long agreementId;

    @Schema(description = "产品连接方式")
    @NotNull(message = "产品连接方式不能为空")
    private Integer deviceType;

    @Schema(description = "所属边缘计算")
    private Long edgeId;

}
