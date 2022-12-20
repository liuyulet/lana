package com.lana.modules.system.pojo.vo;

import com.lana.modules.system.pojo.entity.SysDemanUserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 人员意见表(OaBusUserOpinion)实体类
 *
 * @author makejava
 * @since 2022-11-10 20:47:05
 */
@Data
@ApiModel("进度详情实体对象")
public class SysDemanUserVO implements Serializable {
    private static final long serialVersionUID = 941798154369033709L;

    /**
     * 节点id
     */
    @ApiModelProperty(value = "节点id",required = true)
    private Long nodeId;

    /**
     * 人员信息
     */
    @ApiModelProperty(value = "实体",required = true)
    private List<SysDemanUserEntity> sysDemanUserEntity;



}

