package com.lana.modules.system.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysUserDepartRole)表实体类
 *
 * @author makejava
 * @since 2022-10-04 19:42:17
 */
@Data
@ApiModel("人员全信息实体信息")
public class SysUserDepartRoleVO {
    //姓名
    @ApiModelProperty("姓名")
    private String fullname;
    //邮箱
    @ApiModelProperty("邮箱")
    private String email;
    //用户账号名
    @ApiModelProperty("用户账号名")
    private String username;
    //手机号
    @ApiModelProperty("手机号")
    private String mobile;
    //状态  0：禁用   1：正常       2:新注册
    @ApiModelProperty("状态  0：禁用   1：正常       2:新注册")
    private Integer status;
    //角色ID
    @ApiModelProperty("角色ID")
    private Long roleid;
    @ApiModelProperty("用户id")
    private Long userId;
    //负责部门
    @ApiModelProperty("负责部门")
    private String dutyIds;
    //创建者ID
    @ApiModelProperty("创建者ID")
    private String createUser;
    //创建时间
    @ApiModelProperty("创建时间")
    private Date createTimer;
    //机构/部门名称
    @ApiModelProperty("机构/部门名称")
    private String departName;
    @ApiModelProperty("机构id")
    private Long departId;
    //角色名称
    @ApiModelProperty("角色名称")
    private String roleName;
    //机构/部门名称
    @ApiModelProperty("负责机构/部门名称")
    private String dutyName;




}

