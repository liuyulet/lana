
package com.lana.modules.system.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * 密码表单
 *
 * @auther liuyulet
 */
@Data
@ApiModel("过程节点用户信息")
public class StepNodeDTO {
    /**
     * 过程名称
     */
    @ApiModelProperty("过程名称")
    private String stepName;
    /**
     * 用户信息
     */
    @ApiModelProperty("过程节点绑定的用户信息")
    private Object userSelection;
    /**
     * 过程节点信息
     */
    @ApiModelProperty("过程节点信息")
    private Object stepList;

}
