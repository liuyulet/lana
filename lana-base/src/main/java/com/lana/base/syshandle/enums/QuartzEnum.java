package com.lana.base.syshandle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据范围枚举
 */
@Getter
@AllArgsConstructor
public enum QuartzEnum {
    /**
     *默认作业组
     */
    DEFAULT_JOB_GROUP("lana_job_group"),
    /**
     *默认触发器组
     */
    DEFAULT_TRIGGER_GROUP("lana_trigger_group"),

    /**
     * 触发 PRE
     */
    TRIGGER_PRE("Trigger_"),

    /**
     * 作业类名称
     */
    JOB_CLASS_NAME("com.lana.rules.executes.job.SchedulerJob");


    private final String value;

}