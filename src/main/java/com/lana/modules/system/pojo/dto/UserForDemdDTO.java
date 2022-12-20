
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
@ApiModel("需求分配用户")
public class UserForDemdDTO {
    /**
     * 需求id
     */
    @ApiModelProperty("角色id")
    private Integer demendId;
    /**
     * 新增绑定用户id
     */
    @ApiModelProperty("新增加要绑定的用户id：userId，该对象是int[]数组")
    private int[] userId;

    /**
     * 授权人
     */
    @ApiModelProperty("授权的人是谁")
    private String userName;


}
