package com.lana.modules.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysDeman)实体类
 *
 * @author liuyulet
 * @since 2022-09-30 11:25:38
 */
@Data
@TableName("sys_deman")
public class SysDeman implements Serializable {
    private static final long serialVersionUID = 139595945385381373L;

    @TableId
    private Long id;
    /**
     * 任务编号，日期+时间戳
     */
    private String demanName;
    /**
     * 任务编号，日期+时间戳
     */
    private String demanNum;
    /**
     * 交底文件地址
     */
    private String demanDisclose;
    /**
     * 需求负责人，由创建需求的时候指定
     */
    private String demanConsci;
    /**
     * 任务截止时间
     */
    private Date demanDeadline;
    /**
     * 1:新建待分配，2：已分配，3:变更中，4：变更完成；
        11:开发中，12:开发完成；
        21:待测试，22:测试中，23:测试完成；
        31:产品代验收，32：验收检查，33:验收完成；
        41：待实施，42：实施中，43:实施完成
     */
    private Integer demanStatus;
    /**
     * 变更次数
     */
    private Integer demanChange;
    /**
     * 所属项目编码
     */
    private String demanProject;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;


}

