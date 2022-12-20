

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
@ApiModel("修改密码")
public class PasswordDTO {
    /**
     * 原密码
     */
    @ApiModelProperty("原密码")
    private String password;
    /**
     * 新密码
     */
    @ApiModelProperty("新密码")
    private String newPassword;

}
