
package com.lana.modules.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户与角色对应关系
 * @auther liuyulet
 * @date 2022/9/27 12:54
 */
@Data
@TableName("sys_user_role")
public class SysUserRoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;


}
