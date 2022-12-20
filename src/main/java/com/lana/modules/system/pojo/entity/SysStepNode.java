package com.lana.modules.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (SysStepNode)实体类
 *
 * @author liuyulet
 * @since 2022-10-22 14:31:45
 */
@Data
@TableName("sys_step_node")
public class SysStepNode implements Serializable {
    private static final long serialVersionUID = -38535541933056292L;
    @TableId
    private Long id;
    /**
     * 过程id
     */
    private Long stepId;
    /**
     * 过程节点名称
     */
    private String stepNodeLabel;
    /**
     * 过程节点id
     */
    private Long stepNodeValue;


}

