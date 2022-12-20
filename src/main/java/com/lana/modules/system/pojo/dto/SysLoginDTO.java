

package com.lana.modules.system.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录表单
 * @auther liuyulet
 */
@Data

public class SysLoginDTO {

    private String username;

    private String password;

    private String captcha;

    private String uuid;


}
