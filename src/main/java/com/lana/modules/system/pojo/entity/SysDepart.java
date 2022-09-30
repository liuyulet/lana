package com.lana.modules.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 组织机构表(SysDepart)实体类
 *
 * @author lyl
 * @since 2022-09-29 11:14:58
 */
@Data
@TableName("sys_depart")
public class SysDepart implements Serializable {
    private static final long serialVersionUID = 705058321493528244L;

    @TableId
    private Long id;
    /**
     * 父机构ID
     */
    private Long parentId;
    /**
     * 机构/部门名称
     */
    private String departName;
    /**
     * 机构编码
     */
    private String departCode;
    /**
     * 状态（1启用，0不启用）
     */
    private Integer departStatus;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;


}

