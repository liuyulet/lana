package com.lana.modules.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (SysStepNodeUser)实体类
 *
 * @author liuyulet
 * @since 2022-10-22 14:31:45
 */
@Data
@TableName("sys_step_node_user")
public class SysStepNodeUser implements Serializable {
    private static final long serialVersionUID = 172006055025268868L;
    @TableId
    private Long nodUserId;
    /**
     * 过程id
     */
    private Long stepId;
    /**
     * 节点id
     */
    private Long nodeId;
    /**
     * 用户id
     */
    private Long userId;



}

