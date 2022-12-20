package com.lana.modules.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户-机构关联表(SysUserDepart)实体类
 *
 * @author liuyulet
 * @since 2022-09-29 10:02:49
 */
@Data
@TableName("sys_user_depart")
public class SysUserDepartEntity implements Serializable {
    private static final long serialVersionUID = 151455860810935026L;
    @TableId
    private Long id;
    /**
     * 组织机构id
     */
    private Long departId;
    /**
     * 用户id
     */
    private long[] userId;


}

