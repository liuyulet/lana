package com.lana.system.entity.vo.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/29 13:41
 */
@Data
@Schema(description = "角色授权接受尸体")
public class SysRoleMenusUpdate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色编码")
    private List<String> menus;

    @Schema(description = "控制台")
    private List<String> grids;

    @Schema(description = "数据类型")
    private Integer grid;

    @Schema(description = "角色id")
    private Long roleId;

}
