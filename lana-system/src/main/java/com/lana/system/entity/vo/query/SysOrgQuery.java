package com.lana.system.entity.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther liuyulet
 * @date 2024/3/30 13:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "组织角色")
public class SysOrgQuery{

    @Schema(description = "组织角色")
    private String name;

}
