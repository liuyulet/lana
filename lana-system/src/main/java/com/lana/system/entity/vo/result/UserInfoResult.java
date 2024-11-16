package com.lana.system.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @auther liuyulet
 * @date 2024/4/3 09:40
 *
 */
@Data
@Schema(description = "用户信息")
public class UserInfoResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String dashboard;
    private String userName;
    private Set<String> role;
    private String realName;
    private Integer gender;
    private String signature;
    private String avatar;
    private String mobile;
    private String email;

}
