package com.lana.device.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lana.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("device_item")
public class DeviceItemEntity extends BaseEntity {
    /**
     * 设备图片
     */
    private String imgs;
    /**
     * 设备名称
     */
    private String name;
    /**
     * 设备编码
     */
    private String deviceNumber;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 产品类型id
     */
    private Long productTypeId;

    /**
     * 产品类型id
     */
    private String productTypeShow;

}
