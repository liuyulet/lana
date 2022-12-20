
package com.lana.modules.system.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 密码表单
 *
 * @auther liuyulet
 */
@Data
@ApiModel("协作者实体对象")
public class LookCollaVO {
    /**
     * id
     */
    @ApiModelProperty("角色id")
    private Integer id;
    /**
     * 任务状态
     */
    @ApiModelProperty("任务状态。0:未开始，1:进行中，2:已完成")
    private Integer taskStatus;

    /**
     * 提交信息
     */
    @ApiModelProperty("提交信息")
    private String taskRecord;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private String taskStartTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private String taskEndTime;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String fullname;

    /**
     * 用户账号
     */
    @ApiModelProperty("用户账号")
    private String username;

    
    /**
     * 用户角色
     */
    @ApiModelProperty("用户角色")
    private String roleName;



}
