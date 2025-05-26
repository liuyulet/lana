package com.lana.rules.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class DeviceControlEntity{
     private Long id;

     private String deviceItemName;
     /**
     * 设备id
     */
     private Long deviceItemId;

     /**
     * 功能名称
     */
     private String controlName;
     /**
     * 属性标志
     */
     private String controlMode;

     /**
     * 属性值
     */
     private String controlDefultValue;

}
