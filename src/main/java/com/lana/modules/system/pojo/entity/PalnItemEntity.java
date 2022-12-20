package com.lana.modules.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (PalnItem)实体类
 *
 * @author makejava
 * @since 2022-11-03 15:16:12
 */
@Data
@TableName("paln_item")
public class PalnItemEntity implements Serializable {
    private static final long serialVersionUID = -93446257192429639L;
    
    private Long id;
    /**
     *  任务名称
     */
    private String planName;
    /**
     * 计划内容描述
     */
    private String planCont;
    /**
     * 0:新建，1:进行中，2:完成，3:驳回，
     */
    private Integer planStatus;
    /**
     * 关联计划名称
     */
    private String planDemanName;
    /**
     * 计划完成日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date planDemanEndTime;
    /**
     * 关联的计划id
     */
    private Long planDemanId;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;

}

