package com.lana.system.entity.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @auther liuyulet
 * @date 2024/3/30 14:40
 */
@Data
@Schema(description = "机构")
public class SysOrgSave {


    @Schema(description = "上级ID", required = true)
    private Long pid;

    @Schema(description = "机构名称", required = true)
    @NotBlank(message = "机构名称不能为空")
    private String name;

    @Schema(description = "排序", required = true)
    @Min(value = 0, message = "排序值不能小于0")
    private Integer sort;

    @Schema(description = "是否启用", required = true)
    private Integer status;

    @Schema(description = "备注")
    private String remark;
}
