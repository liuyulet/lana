package com.lana.system.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @auther liuyulet
 * @date 2024/3/21 15:59
 */
@Data
@Schema(description = "图片验证码")
public class SysCaptchaResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "key")
    private String key;

    @Schema(description = "image base64")
    private String image;

    @Schema(description = "是否开启验证码")
    private Boolean captchaEnabled;
}
