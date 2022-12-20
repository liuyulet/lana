
package com.lana.modules.system.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 密码表单
 *
 * @auther liuyulet
 */
@Data
@ApiModel("我得任务实体对象")
public class TaskUserDataVO {
    /**
     * 任务id
     */
    @ApiModelProperty("任务id")
    private Long id;
    /**
     * 计划名称
     */
    @ApiModelProperty("计划名称")
    private String demanName;

    /**
     * 交底文件地址
     */
    @ApiModelProperty("交底文件地址")
    private String demanFileUrl;

    /**
     * 交底文件名称
     */
    @ApiModelProperty("交底文件名称")
    private String demanFileName;

    /**
     * 计划编号
     */
    @ApiModelProperty("计划编号")
    private String demanNum;

    /**
     * 计划负责人
     */
    @ApiModelProperty("计划负责人")
    private String demanPersName;

    /**
     * 计划完成时间
     */
    @ApiModelProperty("计划完成时间")
    private Date damanEndTime;

    
    /**
     * 计划创建时间
     */
    @ApiModelProperty("计划创建时间")
    private Date demanCreateTime;


    /**
     * 计划创建时间
     */
    @ApiModelProperty("计划中任务的名称")
    private String planName;
    /**
     * 计划创建时间
     */
    @ApiModelProperty("计划中任务的内容")
    private String planCont;
    /**
     * 计划创建时间
     */
    @ApiModelProperty("计划中任务的状态：0:新建，1:进行中，2:完成，3:驳回，4:已分配")
    private Integer planStatus;
    /**
     * 计划创建时间
     */
    @ApiModelProperty("计划中任务的结束时间")
    private Date planEndTime;
    /**
     * 计划创建时间
     */
    @ApiModelProperty("计划中任务的创建时间")
    private Date planCreatTime;
    /**
     * 计划创建时间
     */
    @ApiModelProperty("自己任务状态1:进行中，2:驳回，3应做，0待办（刚分配），只有为3的时候才会显示到任务中")
    private Integer taskMeStatus;
    /**
     * 计划创建时间
     */
    @ApiModelProperty("计划中任务的整体数据状态。1:任务被驳回，2:任务完成，3:进行中")
    private Integer taskDataStatus;



}
