package com.lana.device.entity.vo.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@Schema(description = "保存绑定分组设备")
public class GroupDevicedate {


    @Schema(description = "所绑定的分组id")
    private Long groupId;


    @Schema(description = "所选择的设备id")
    private Long deviceId;
}
