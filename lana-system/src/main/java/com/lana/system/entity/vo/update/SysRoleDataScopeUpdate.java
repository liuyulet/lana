package com.lana.system.entity.vo.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/29 17:18
 */
@Data
@Schema(description = "角色数据权限")
public class SysRoleDataScopeUpdate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @NotNull(message = "角色ID不能为空")
    private Long id;

    @Schema(description = "数据范围  0：全部数据  1：本机构及子机构数据  2：本机构数据  3：本人数据  4：自定义数据")
    @NotNull(message = "数据范围不能为空")
    private Integer dataScope;

    @Schema(description = "机构ID列表")
    private List<Long> orgIdList;
}
