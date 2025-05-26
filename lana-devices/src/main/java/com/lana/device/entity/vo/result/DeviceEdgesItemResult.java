package com.lana.device.entity.vo.result;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.mybatis.entity.BaseEntity;
import com.lana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/10/25 16:46
 */
@Data
@Schema(description = "边缘计算实体数据")
public class DeviceEdgesItemResult {

    @Schema(description = "id")
    private Long id;
    /**
     * 边缘编码（非必填）
     */
    @Schema(description = "边缘编码（非必填）")
    private String edgeSn;
    /**
     * 边缘名称
     */
    @Schema(description = "边缘名称")
    private String edgeName;
    /**
     * 固件版本
     */
    @Schema(description = "固件版本")
    private String edgeFirmwareVersion;

    /**
     * 秘钥
     */
    @Schema(description = "秘钥")
    private String edgeSecretKey;

    /**
     * 秘钥id
     */
    @Schema(description = "秘钥id")
    private String edgeSecretId;

    /**
     * 经度
     */
    @Schema(description = "经度")
    private String edgeLon;

    /**
     * 维度
     */
    @Schema(description = "维度")
    private String edgeLat;

    /**
     * 所属产品类型id
     */
    @Schema(description = "所属产品类型id")
    private String edgeProductTypeId;

    /**
     * 所属产品类型id
     */
    @Schema(description = "所属产品名称")
    private String edgeProductTypeName;

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
