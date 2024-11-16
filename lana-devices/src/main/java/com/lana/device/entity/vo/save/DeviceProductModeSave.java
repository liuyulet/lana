package com.lana.device.entity.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@Schema(description = "产品物模型存储对象实体")
public class DeviceProductModeSave {


    /**
     * 产品物模型实例
     */
    @Schema(description = "产品物模型实例")
    private List<DeviceProductModeListSave> deviceProductModeListSave;
    /**
     * 设备实例id
     */
    @Schema(description = "设备实例id")
    private Long productTypeId;

}
