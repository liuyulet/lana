package com.lana.rules.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.base.syshandle.enums.QuartzEnum;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.rules.entity.JobInfoEntity;
import com.lana.rules.service.SchedulerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @auther liuyulet
 * @date 2025/2/26 21:28
 */
@RestController
@RequestMapping("/rules/quartz")
@Tag(name = "任务处理")
@ApiSupport(author = "liuyulet")
public class SchedulerController {


    @Autowired
    private SchedulerService schedulerService;

    @PostMapping("/createJob")
    @Operation(summary = "新增定时任务")
    public LanaResult createJob(@RequestBody JobInfoEntity jobInfo) {
        return LanaResult.ok( schedulerService.addCronJob(jobInfo.getJobName(), jobInfo.getCron(), QuartzEnum.JOB_CLASS_NAME.getValue()));
    }

    @PostMapping("/deleteJob")
    @Operation(summary = "删除定时任务")
    public LanaResult deleteJob(@RequestBody JobInfoEntity jobInfo) {
        schedulerService.deleteCronJob(jobInfo.getJobName(), jobInfo.getJobGroup(), jobInfo.getTriggerName(), jobInfo.getTriggerGroup());
        return LanaResult.ok();
    }

    @PostMapping("/executeImmediately")
    @Operation(summary = "立即执行定时任务")
    public LanaResult executeImmediately(@RequestBody JobInfoEntity jobInfo) {
        schedulerService.executeImmediately(jobInfo.getJobName(), QuartzEnum.JOB_CLASS_NAME.getValue());
        return LanaResult.ok();
    }


}
