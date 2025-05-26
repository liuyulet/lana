package com.lana.rules.entity;

import lombok.Data;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:31
 */
@Data
public class RulesJobPushDeviceEntity {

    private Long id;
    /**
     * 监听任务中的设备ids,多个id，用，号隔开
     */
    private String deviceIds;

    /**
     * 规则id
     */
    private Long ruleId;

}
