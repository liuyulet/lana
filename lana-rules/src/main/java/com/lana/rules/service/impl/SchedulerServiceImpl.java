package com.lana.rules.service.impl;

import com.lana.base.scheduler.SchedulerUtils;
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
    public boolean addCronJob(String jobName, String cron, String jobClassName) {
        return schedulerUtils.addCronJob(jobName, cron, jobClassName);
    }

    @Override
    public void deleteCronJob(String jobName, String jobGroup, String triggerName, String triggerGroup) {
        schedulerUtils.deleteCronJob(jobName,jobGroup,triggerName,triggerGroup);
    }


    @Override
    public void executeImmediately(String jobName, String jobClassName) {
        schedulerUtils.executeImmediately(jobName,jobClassName);
    }


}
