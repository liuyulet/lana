package com.lana.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther liuyulet
 * @date 2024/6/18 11:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_org")
public class SysUserOrgEntity {
    /**
     * id
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long orgId;
}
