package com.lana.system.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @auther liuyulet
 * @date 2024/3/29 17:55
 */
@Data
@Schema(description = "前端需要的数据格式")
public class SysMenusNavResult implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description = "仪表盘")
    private List<String> dashboardGrid;
    @Schema(description = "菜单")
    private List<SysMenusResult> menu;
    @Schema(description = "权限")
    private Set<String> permissions;

}
