package com.lana.device.entity.vo.result;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.syshandle.tree.HandleTree;
import com.lana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@Schema(description = "产品类型")
public class DeviceProductTypeResult extends HandleTree<DeviceProductTypeResult> {
    /**
     * 上级ID
     */
    @Schema(description = "上级ID")
    private Long pid;
    /**
     * 机构名称
     */
    @Schema(description = "产品名称")
    private String label;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 接入方式
     */
    @Schema(description = "接入方式")
    private Integer deviceAbutmentType;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Integer status;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    /**
     * 创建时间
     */
    @Schema(description = "修改时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String creatorName;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private String updaterName;
}
