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
@TableName("device_product_mode")
public class DeviceProductModeEntity extends BaseEntity {
    /**
     * 属性名称
     */
    private String modeName;
    /**
     * 属性标志
     */
    private String modeSigns;

    /**
     * 数据类型
     */
    private String modeDataType;

    /**
     * 事件采集属性  0：否   1：是
     */
    private Integer modeDataSource;

    /**
     * 控制属性  0：否   1：是
     */
    private Integer modeControlAtt;
    /**
     * 补充说明
     */
    private String modeRemark;
    /**
     * 设备实例id
     */
    private Long productTypeId;

}
