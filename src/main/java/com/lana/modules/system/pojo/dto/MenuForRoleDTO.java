
package com.lana.modules.system.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 密码表单
 *
 * @auther liuyulet
 */
@Data
@ApiModel("角色绑定菜单")
public class MenuForRoleDTO {
    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    private Integer roleId;
    /**
     * 绑定菜单id
     */
    @ApiModelProperty("已经绑定的，但是要换绑菜单的 菜单id：menuUpdateId，该对象是int[]数组")
    private int[] menuUpdateId;

}
