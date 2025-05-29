package com.lana.rules.service.impl;

import com.lana.base.scheduler.SchedulerUtils;
import com.lana.base.syshandle.enums.QuartzEnum;
import com.lana.rules.service.SchedulerService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liuyulet
 * @create 2025/2/26 15:38
 */
@Slf4j
@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Resource
    private SchedulerUtils schedulerUtils;


    @Override
    public boolean addCronJob(String jobName, String cron, String jobGroup,String triggerGroup, String jobClassName) {
        return schedulerUtils.addCronJob(jobName, cron, jobGroup,triggerGroup, jobClassName, QuartzEnum.TRIGGER_PRE.getValue());
    }

    @Override
    public void deleteCronJob(String jobName, String jobGroup, String triggerName, String triggerGroup) {
        schedulerUtils.deleteCronJob(jobName,jobGroup,triggerName,triggerGroup);
    }


    @Override
    public void executeImmediately(String jobName,String jobGroup, String triggerGroup, String jobClassName) {
        schedulerUtils.executeImmediately(jobName,jobGroup,triggerGroup,jobClassName);
    }

    //新增日志定时任务
    @Override
    public boolean createLogJob(String jobName, String cron, String jobGroup,String triggerGroup,String jobClassName) {
        return schedulerUtils.addCronJob(jobName, cron, jobGroup,triggerGroup, jobClassName, QuartzEnum.LOG_TRIGGER_PRE.getValue());
    }

    //新增日志定时任务
    @Override
    public boolean createDeviceJob(String jobName, String cron, String jobGroup,String triggerGroup,String jobClassName) {
        return schedulerUtils.addCronJob(jobName, cron, jobGroup,triggerGroup, jobClassName, QuartzEnum.DEVICE_TRIGGER_PRE.getValue());
    }


}
