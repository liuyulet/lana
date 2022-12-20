package com.lana.modules.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("sys_user_depart_role")
public class SysUserDepartRoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //姓名
    private String fullname;
    //邮箱
    private String email;
    //用户账号名
    private String username;
    //手机号
    private String mobile;
    //状态  0：禁用   1：正常       2:新注册
    private Integer status;
    //角色ID
    private Long roleid;
    
    private Long userId;
    //负责部门
    private String dutyIds;
    //创建者ID
    private String createUser;
    //创建时间
    private Date createTimer;
    //机构/部门名称
    private String departName;
    
    private Long departId;
    //角色名称
    private String roleName;
    //机构/部门名称
    private String dutyName;




}

