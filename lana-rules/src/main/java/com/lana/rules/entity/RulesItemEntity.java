package com.lana.rules.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lana.base.mybatis.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("rules_item")
public class RulesItemEntity extends BaseEntity {
    /**
     * 规则脚本名称
     */
    private String asName;
    /**
     * 1:脚本，2:表达式
     */
    private Integer asType;

    /**
     * 表达式内容
     */
    private String asContent;

    /**
     * 路径
     */
    private String asPath;

    /**
     * 规则说明
     */
    private String asRemark;

    /**
     * 是否启用
     */
    private Integer asEnable;

    /**
     * 规则类型
     */
    private Integer ruleType;
}
