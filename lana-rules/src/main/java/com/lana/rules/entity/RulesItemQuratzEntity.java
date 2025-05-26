package com.lana.rules.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("rules_item_quratz")
public class RulesItemQuratzEntity extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 所属规则实例id
     */
    private Long rulesId;
    /**
     * cron表达式
     */
    private String cron;

    /**
     * 作业组
     */
    private String jobGroup;

    /**
     * 触发名称
     */
    private String triggerName;

    /**
     * 触发作业组
     */
    private String triggerGroup;

}
