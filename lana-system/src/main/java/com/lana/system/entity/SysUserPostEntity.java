package com.lana.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lana.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther liuyulet
 * @date 2024/3/28 17:11
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_user_post")
public class SysUserPostEntity extends BaseEntity {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 岗位ID
     */
    private Long postId;
}
