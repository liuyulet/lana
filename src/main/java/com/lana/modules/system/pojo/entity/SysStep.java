package com.lana.modules.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysStep)实体类
 *
 * @author makejava
 * @since 2022-10-22 14:31:42
 */
@Data
@TableName("sys_step")
public class SysStep implements Serializable {
    private static final long serialVersionUID = -51378851138993989L;
    /**
     * 步骤id
     */
    @TableId(type = IdType.INPUT)
    private Long stepId;
    /**
     * 步骤名称
     */
    private String stepName;
    /**
     * 步骤节点数
     */
    private String stepNum;
    /**
     * 创建者ID
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;


}

