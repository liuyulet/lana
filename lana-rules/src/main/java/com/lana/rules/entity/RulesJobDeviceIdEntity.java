package com.lana.rules.entity;

import lombok.Data;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:31
 */
@Data
public class RulesJobDeviceIdEntity {

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
