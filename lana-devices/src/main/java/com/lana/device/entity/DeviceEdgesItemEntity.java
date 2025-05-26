package com.lana.device.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lana.base.mybatis.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/10/25 16:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("device_edges_item")
public class DeviceEdgesItemEntity extends BaseEntity {
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


}
