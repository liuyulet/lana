package com.lana.system.entity.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther liuyulet
 * @date 2024/3/31 17:44
 */
@Data
@Schema(description = "字典类型")
public class SysDictTypeResult implements Serializable {
    private static final long serialVersionUID = 1L;


    @Schema(description = "id")
    private Long id;

    @Schema(description = "字典编码", required = true)
    @NotBlank(message = "字典类型不能为空")
    private String code;

    @Schema(description = "字典名称", required = true)
    @NotBlank(message = "字典名称不能为空")
    private String name;

    @Schema(description = "排序")
    private Integer sort;


    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;

}
