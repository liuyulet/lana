package com.lana.modules.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysDemanUser)实体类
 *
 * @auther liuyulet
 * @since 2022-10-07 23:16:35
 */
@Data
@TableName("sys_deman_user")
public class SysDemanUserEntity implements Serializable {
    private static final long serialVersionUID = 491299544237945298L;
    @TableId
    private Long id;
    /**
     * 任务id
     */
    private Long palnItemId;
    /**
     * 需求id
     */
    private String palnItemName;

    /**
     * 计划名称
     */
    private String palnName;

    /**
     * 过程id；
     */
    private Long stepId;

    /**
     * 节点名称
     */
    private Long nodeId;

    /**
     * 用户id
     */
    private Long userId;


    /**
     * 用户名称
     */
    private String userName;


    /**
     * 自己任务状态1:接受，2:驳回，3应做，0预置（刚分配）
     */
    private Integer userOpinion;

    /**
     * 整体数据状态。1:任务被驳回，2:任务完成，3:进行中中
     */
    private Integer dataType;

    /**
     * 提交内容/驳回意见
     */
    private String userRemarks;

    /**
     * 完成/驳回时间
     */
    private Date overTime;

    /**
     * 创建者
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建时间
     */
    private Date updataTime;

}

