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
     * 备注
     */
    private String asPath;
}
