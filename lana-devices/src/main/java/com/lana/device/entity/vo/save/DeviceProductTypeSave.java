package com.lana.device.entity.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@Schema(description = "产品类型")
public class DeviceProductTypeSave{
    /**
     * 上级ID
     */
    @Schema(description = "上级ID")
    private Long pid;
    /**
     * 机构名称
     */
    @Schema(description = "机构名称")
    @NotBlank(message = "机构名称不能为空")
    private String label;
    /**
     * 排序
     */
    @Schema(description = "排序")
    @Min(value = 0, message = "排序值不能小于0")
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
