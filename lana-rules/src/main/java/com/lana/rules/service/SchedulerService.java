package com.lana.rules.service;

/**
*@author liuyulet
*@create 2025/2/26 15:31
*/
public interface SchedulerService {

    /**
     * 添加定时任务
     *
     * @param jobName           任务名
     * @param cron              cron表达式
     * @param jobClassName      任务类名
     * @return
     */
    boolean addCronJob(String jobName, String cron, String jobGroup,String triggerGroup, String jobClassName);


    /**
     * 删除定时任务
     *
     * @param jobName           任务名
     * @param jobGroup          任务组名
     * @param triggerName       触发器名
     * @param triggerGroup      触发器组名
     * @return
     */
    void deleteCronJob(String jobName, String jobGroup, String triggerName, String triggerGroup);


    /**
     * 立即执行一次定时任务
     *
     * @param jobName           任务名
     * @param jobClassName      任务类名
     * @return
     */
    void executeImmediately(String jobName,String jobGroup, String triggerGroup, String jobClassName);


    boolean createLogJob(String jobName, String cron, String jobGroup,String triggerGroup,String jobClassName);

    boolean createDeviceJob(String jobName, String cron, String jobGroup,String triggerGroup,String jobClassName);
}
