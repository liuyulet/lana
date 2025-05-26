package com.lana.rules.entity;

import lombok.Data;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:31
 */
@Data
public class RulesActionInstancEntity {

    private Long id;
    /**
     * 动作编码
     */
    private String acCode;

    /**
     * 动作内容
     */
    private String acInstancing;
    /**
     * 规则id
     */
    private Long ruleId;
}
