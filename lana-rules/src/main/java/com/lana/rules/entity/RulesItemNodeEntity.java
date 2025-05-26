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
@TableName("rules_item_node")
public class RulesItemNodeEntity extends BaseEntity {
    /**
     * 所属规则实例id
     */
    private Long rulesId;

    /**
     * 实例名称
     */
    private String name;

    /**
     * 节点设置
     */
    private String nodeConfig;


}
