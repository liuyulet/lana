package com.lana.system.entity.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.utils.DateUtils;
import com.lana.base.syshandle.tree.HandleTree;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @auther liuyulet
 * @date 2024/3/30 14:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "机构")
public class SysOrgResult extends HandleTree<SysOrgResult> {

    @NotBlank(message = "id")
    private Long pid;

    @NotBlank(message = "机构名称")
    private String name;

    @NotBlank(message = "机构名称,用于前端新增树形列表部门列表")
    private String label;


    @Schema(description = "排序", required = true)
    private Integer sort;

    @Schema(description = "是否启用", required = true)
    private Integer status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    @Schema(description = "修改时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;


}
