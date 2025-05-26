package com.lana.base.syshandle.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设备控制
 * @author liuyulet
 * @create 2025/4/15 13:49
 */

@Data
public class DeviceControl {

    /**
     * id
     */
    private Long id;
    /**
     * 设备id
     */
    private Long deviceItemId;
    /**
     * 功能类型
     */
    private Integer controlType;
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
     * 发送数据属性值
     */
    private String controlDefultValue;
}
