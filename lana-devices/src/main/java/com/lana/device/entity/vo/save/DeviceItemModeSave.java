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
@Schema(description = "设备物模型")
public class DeviceItemModeSave {

    /**
     * 产品物模型实例
     */
    @Schema(description = "设备物模型实例")
    private List<DeviceModeListSave> deviceModeListSave;
    /**
     * 设备实例id
     */
    @Schema(description = "设备实例id")
    private Long deviceItemId;

}
