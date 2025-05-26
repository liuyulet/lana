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
@Schema(description = "设备实例--分组列表查询")
public class GroupDeviceItemResult {

    @Schema(description = "id")
    private Long id;
    /**
     * 设备分组id
     */
    @Schema(description = "id")
    private Long groupId;
    /**
     * 设备图片
     */
    @Schema(description = "设备图片")
    private String imgs;
    /**
     * 设备名称
     */
    @Schema(description = "设备名称")
    private String name;
    /**
     * 设备编码
     */
    @Schema(description = "设备编码")
    private String deviceNumber;

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
     * 产品类型id
     */
    @Schema(description = "产品类型名称")
    private String productTypeName;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;


    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String creatorName;

}
