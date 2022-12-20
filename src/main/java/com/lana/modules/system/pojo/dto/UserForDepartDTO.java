
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
@ApiModel("用户绑定组织")
public class UserForDepartDTO {
    /**
     * 机构id
     */
    @ApiModelProperty("机构id")
    private Integer departId;
    /**
     * 新增绑定用户id
     */
    @ApiModelProperty("新增加要绑定的用户id：userId，该对象是int[]数组")
    private int[] userId;
    /**
     * 修改绑定用户id
     */
    @ApiModelProperty("已经绑定的，但是要换绑用户id：userUpdateId，该对象是int[]数组")
    private int[] userUpdateId;

}
