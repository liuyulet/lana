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
@TableName("device_control_data")
public class DeviceControlDataEntity extends BaseEntity {
     /**
     * 设备id
     */
     private Long deviceItemId;
     /**
     * 功能类型
     */
     private Integer controlType;
     /**
     * 控制类型名称
     */
     private String controlTypeLabel;
     /**
     * 功能名称
     */
     private String controlName;
     /**
     * 属性标志
     */
     private String controlMode;
     /**
     * 数据类型
     */
     private String controlDataType;
     /**
     * 属性类型
     */
     private String controlDataTypeLabel;
     /**
     * 属性值
     */
     private String controlDefultValue;

}
