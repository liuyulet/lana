package com.lana.system.entity.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/28 18:03
 */
@Data
@Schema(description = "角色")
public class SysRoleResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @Schema(description = "角色编码")
    @NotBlank(message = "角色编码不能为空")
    private String roleCode;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "数据范围  0：全部数据  1：本机构及子机构数据  2：本机构数据  3：本人数据  4：自定义数据")
    private Integer dataScope;

    @Schema(description = "菜单ID列表")
    private List<Long> menuIdList;

    @Schema(description = "机构ID列表")
    private List<Long> orgIdList;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "是否启用")
    private Integer status;
}
