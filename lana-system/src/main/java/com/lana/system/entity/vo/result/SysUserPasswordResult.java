package com.lana.system.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @auther liuyulet
 * @date 2024/3/28 17:35
 */
@Data
@Schema(description = "用户修改密码")
public class SysUserPasswordResult implements Serializable {

    @Schema(description = "用户id", required = true)
    private Long id;

}
