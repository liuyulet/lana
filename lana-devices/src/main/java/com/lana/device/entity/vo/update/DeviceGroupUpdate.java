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
@Schema(description = "产品分组")
public class DeviceGroupUpdate {


    @Schema(description = "id")
    private Long id;

    /**
     * 设备分组
     */
    @Schema(description = "设备分组")
    private String name;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
}
