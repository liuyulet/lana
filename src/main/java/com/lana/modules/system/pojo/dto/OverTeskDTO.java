

package com.lana.modules.system.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 密码表单
 *
 * @auther liuyulet
 */
@Data
@ApiModel("任务完成")
public class OverTeskDTO {
    /**
     * 需求id
     */
    @ApiModelProperty("任务id")
    private Integer demanId;

    /**
     * 提交内容
     */
    @ApiModelProperty("提交内容")
    private String contents;

    /**
     * 完成任务还是驳回任务。(1是0否)
     */
    @ApiModelProperty("完成任务还是驳回")
    private Integer taskType;


}
