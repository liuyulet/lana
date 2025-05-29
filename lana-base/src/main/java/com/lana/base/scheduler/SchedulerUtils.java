package com.lana.base.scheduler;

import com.lana.base.syshandle.enums.QuartzEnum;
import com.lana.base.syshandle.exception.LanaException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * @author liuyulet
 * @create 2025/3/10 17:29
 */
@Slf4j
@Component
public class SchedulerUtils {


    @Resource
    private org.quartz.Scheduler scheduler;


    public boolean addCronJob(String jobName, String cron,String jobGroup,String triggerGroup, String jobClassName,String triggerPre) {
        try {
            // 当前任务不存在才进行添加
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            if (scheduler.checkExists(jobKey)) {
                log.info("[添加定时任务]已存在该作业，jobkey为：{}", jobKey);
                return false;
            }
            // 构建 Job
            JobDetail job = JobBuilder.newJob(getClass(jobClassName).getClass())
                    .withIdentity(jobKey).build();
            // cron表达式定时构造器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            // 构建 Trigger
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(TriggerKey.triggerKey(triggerPre+ jobName, triggerGroup))
                    .withSchedule(cronScheduleBuilder).build();

            // 启动调度器
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
            log.info("[添加定时任务]添加成功，添加内容为：{}", jobName);
            return true;
        } catch (Exception e) {
            log.error("[新增定时任务]失败，报错：", e);
            throw new LanaException("[新增定时任务]失败，报错：" + e.getMessage());
        }
    }

    public void deleteCronJob(String jobName, String jobGroup, String triggerName, String triggerGroup) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);

            Trigger trigger = scheduler.getTrigger(triggerKey);

            if (null == trigger) {
                log.info("[停止定时任务]根据triggerName:{}和triggerGroup:{}未查询到相应的trigger！", triggerName, triggerGroup);
                return;
            }
            // 暂停触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(jobKey);
            log.info("[停止定时任务]jobName:{},jobGroup:{}, triggerName:{}, triggerGroup:{},停止--------------", jobName, jobGroup, triggerName, triggerGroup);
        } catch (SchedulerException e) {
            log.error("[停止定时任务]失败，报错：", e);
            throw new LanaException("[停止定时任务]失败，报错：" + e.getMessage());
        }
    }

    public static Job getClass(String className) throws Exception {
        Class<?> classTemp = Class.forName(className);
        return (Job) classTemp.newInstance();
    }

    public void executeImmediately(String jobName,String jobGroup,String triggerGroup, String jobClassName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            JobDetail job = JobBuilder.newJob(getClass(jobClassName).getClass())
                    .withIdentity(jobKey).build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(TriggerKey.triggerKey(QuartzEnum.TRIGGER_PRE.getValue() + jobName, triggerGroup))
                    .build();

            // 启动调度器
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (Exception e) {
            log.error("[立即执行一次任务，不定时]失败，报错：", e);
            throw new LanaException("[立即执行一次任务，不定时]失败，报错：" + e.getMessage());
        }
    }

}
