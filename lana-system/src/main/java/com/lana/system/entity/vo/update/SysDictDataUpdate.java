package com.lana.system.entity.vo.update;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther liuyulet
 * @date 2024/3/31 22:32
 */
@Data
@Schema(description = "修改字典数据")
public class SysDictDataUpdate implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "字典类型ID", required = true)
    private Long dic;

    @Schema(description = "字典标签", required = true)
    private String name;

    @Schema(description = "字典值")
    private String key;

    @Schema(description = "备注")
    private String yx;

    @Schema(description = "排序", required = true)
    @Min(value = 0, message = "排序值不能小于0")
    private Integer sort;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;
}
