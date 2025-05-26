package com.lana.rules.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liuyulet
 * @create 2025/2/26 15:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class JobInfoEntity {

    /**
     * 任务名称
     * 可以绑定为规则实例的编码，用于确定唯一性
     */
    private String jobName;

    /**
     * cron表达式
     */
    private String cron;

    /**
     * 任务组名
     */
    private String jobGroup;

    /**
     * 任务执行类
     */
    private String triggerName;

    /**
     * 任务执行类
     */
    private String triggerGroup;

}
