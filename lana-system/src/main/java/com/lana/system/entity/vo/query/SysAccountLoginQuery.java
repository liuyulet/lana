package com.lana.system.entity.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @auther liuyulet
 * @date 2024/3/25 11:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "账号登录")
public class SysAccountLoginQuery {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "唯一key")
    private String key;

    @Schema(description = "验证码")
    private String captcha;
}
