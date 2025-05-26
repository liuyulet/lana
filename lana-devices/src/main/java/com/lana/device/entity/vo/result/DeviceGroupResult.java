package com.lana.device.entity.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@Schema(description = "设备分组")
public class DeviceGroupResult {

    @Schema(description = "id")
    private Long id;

    /**
     * 设备分组
     */
    @Schema(description = "设备分组名称")
    private String name;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

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
