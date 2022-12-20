package com.lana.modules.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysTask)实体类
 * @auther liuyulet
 * @since 2022-10-07 21:45:13
 */
@Data
@ApiModel("任务视图实体对象")
@TableName("sys_task")
public class SysTaskEntity implements Serializable {
    private static final long serialVersionUID = 559480854342635621L;

    /**
     * 任务id
     */
    @TableId
    private Integer id;
    /**
     * 任务名称
     */
    private String demanName;
    /**
     * 任务编号，日期+时间戳
     */
    private String demanNum;
    /**
     * 交底文件地址（任务说明）
     */
    private String demanDisclose;
    /**
     * 交底文件名称
     */
    private String demanDisoName;
    /**
     * 关联项目id
     */
    private String demanProject;
    /**
     * 项目名称
     */
    private String demanProjectNam;
    /**
     * 需求负责人id，由创建需求的时候指定
     */
    private String demanConsci;
    /**
     * 负责人名字账号
     */
    private String demanConsciAcoun;
    /**
     * 任务截止时间
     */
    private Date demanDeadline;
    /**
     * 0”>删除/舍弃
1">新建
2">已分配
3">开发中
4">开发完成
5">待测试
6">测试中
7">测试完成
8">产品代验收
9">验收检查
10">验收完成
11">待实施
12">实施中
13">实施完成

     */
    private Integer demanStatus;
    /**
     * 变更次数
     */
    private Integer demanChange;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 需求id
     */
    private Integer demanId;
    /**
     * 0:未开始
     1:进行中
     2:已完成
     */
    private Integer taskStatus;
    /**
     * 任务、代码、测试说明提交记录；
     */
    private String taskRecord;
    /**
     * 任务开始时间
     */
    private Date taskStartTime;
    /**
     * 任务结束时间
     */
    private Date taskEndTime;
    /**
     * 任务分配人员
     */
    private String createUser;
    /**
     * 任务分配时间
     */
    private Date createTime;


}

