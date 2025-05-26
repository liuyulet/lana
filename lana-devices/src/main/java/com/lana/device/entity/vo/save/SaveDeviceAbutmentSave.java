package com.lana.device.entity.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/8/30 13:14
 */
@Data
@Schema(description = "设备关联Mqtt协议")
public class SaveDeviceAbutmentSave {

    @Schema(description = "产品id")
    private Long productTypeId;

    @Schema(description = "协议id")
    private Long agreementId;

    @Schema(description = "产品连接方式")
    private Integer deviceType;

    @Schema(description = "所属边缘计算/关联边缘计算实例信息")
    private Long edgeId;

}
