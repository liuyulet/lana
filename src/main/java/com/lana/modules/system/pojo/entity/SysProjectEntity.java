package com.lana.modules.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysProject)实体类
 *
 * @auther liuyulet
 * @since 2022-10-06 11:23:37
 */
@Data
@ApiModel("项目实体对象")
@TableName("sys_project")
public class SysProjectEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("项目id，修改项目信息的时候需要携带")
    @TableId
    private Long projectId;
    /**
     * 项目名称
     */
    @ApiModelProperty("新增加要绑定的用户id：userId，该对象是int[]数组")
    private String projectName;
    /**
     * 项目编号
     */
    @ApiModelProperty("项目编号")
    private String projectNum;
    /**
     * 项目负责人姓名
     */
    @ApiModelProperty("项目负责人姓名")
    private String projectDutyUser;
    /**
     * 项目负责人账号
     */
    @ApiModelProperty("项目负责人账号")
    private String projectDutyAcco;
    /**
     * 章程文件链接
     */
    @ApiModelProperty("章程文件链接")
    private String projectConstitution;
    /**
     * 项目状态
     */
    @ApiModelProperty("项目状态")
    private Integer projectStatus;
    /**
     * 项目备注
     */
    @ApiModelProperty("项目备注")
    private String projectMakr;
    /**
     * 创建者ID
     */
    @ApiModelProperty("创建者账号")
    private String createUser;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间，后端默认生成请求该接口的时间")
    private Date createTime;


}

