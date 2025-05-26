package com.lana.rules.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lana.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:31
 */
@Data
public class RulesListenDeviceIdEntity {

    private Long id;
    /**
     * 监听任务中的设备id
     */
    private Long deviceId;

    /**
     * 规则id
     */
    private Long ruleId;

}
