package com.lana.system.entity.vo.save;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/28 17:41
 */
@Data
@Schema(description = "用户保存实体")
public class SysUserSave implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Schema(description = "姓名", required = true)
    @NotBlank(message = "姓名不能为空")
    private String realName;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别 0：男   1：女   2：未知", required = true)
    //@Range(min = 0, max = 2, message = "性别不正确")
    private Integer gender;

    @Schema(description = "邮箱")
    //@Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "手机号", required = true)
    private String mobile;

    @Schema(description = "个性签名", required = true)
    //@NotNull(message = "机构ID不能为空")
    private String signature;

    @Schema(description = "状态 0：停用    1：正常", required = true)
    //@Range(min = 0, max = 1, message = "用户状态不正确")
    private Integer status;

    @Schema(description = "所属角色ID列表")
    private List<Long> group;

    @Schema(description = "所属组织ID列表")
    private List<Long> dept;

    @Schema(description = "超级管理员   0：否   1：是")
    private Integer superAdmin;

    @Schema(description = "机构名称")
    private String orgName;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;
}
