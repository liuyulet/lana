package com.lana.system.entity.vo.query;

import com.lana.base.syshandle.page.LanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户查询
 *
 * @auther liuyulet
 * @date 2024/3/20 16:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "用户查询")
public class SysUserQuery extends LanaPageParams {
    @Schema(description = "用户名")
    private String name;

    @Schema(description = "所属部门，即org_id")
    private Integer groupId;


}
