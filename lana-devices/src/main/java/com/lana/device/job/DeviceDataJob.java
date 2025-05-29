package com.lana.device.job;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.RedisCacheOps;
import com.lana.rules.service.DataAnalysisService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyulet
 * @create 2025/5/29 15:48
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class DeviceDataJob implements Job {


    @Value("${lana.devicedata-enabled:false}")
    private boolean loggingEnabled;

    @Value("${lana.device-data-speed:20}")
    private int dataNum;

    @Resource
    private RedisCacheOps redisCacheOps;
    @Resource
    private DataAnalysisService dataAnalysisService;

    private final StringBuilder sb = new StringBuilder();

    private final List<JSONObject> jsdata = new ArrayList<>();

    private List<String> redisData = new ArrayList<>();

    private JSONObject jsonObjects = new JSONObject();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
/*
        log.info("\n\t----------------------------------------------------------------------------\n\t" +
                        "=========================定时任务调度，执行业务逻辑====================:\n\t" +
                        "jobName:{}\n\t" +
                        "jobGroup:{}\n\t" +
                        "triggerName:{}\n\t" +
                        "triggerGroup:{}\n\t" +
                        "上次触发时间:{}\n\t" +
                        "本次触发时间:{}\n\t" +
                        "下次触发时间:{}\n\t" +
                        "调度时间:{}\n\t" +
                        "本次出发内容:{}\n\t" +
                        "----------------------------------------------------------------------------",
                context.getJobDetail().getKey().getName(),
                context.getJobDetail().getKey().getGroup(),
                context.getTrigger().getKey().getName(),
                context.getTrigger().getKey().getGroup(),
                DateUtil.formatDateTime(context.getPreviousFireTime()),
                DateUtil.formatDateTime(context.getFireTime()),
                DateUtil.formatDateTime(context.getNextFireTime()),
                DateUtil.formatDateTime(context.getScheduledFireTime()),
                "设备数据定时触发存储。"
        );*/

        try {
            String key = CacheKeyBuilder.mqttScript();

            int size = 0;
            int count = dataNum;
            jsdata.clear(); //清空列表
            sb.setLength(0); // 清空StringBuilder
            if(jsonObjects!=null){
                jsonObjects.clear();//清空接受数据
            }
            sb.append("INSERT INTO ");
            //获取数据
            jsonObjects = (JSONObject) redisCacheOps.rightPop(key);
            //数据分析
            if(jsonObjects!=null){
                size = 1;
                if(redisData!=null){
                    redisData.clear();
                }
                sb.append("sb");
                sb.append(jsonObjects.getString("deviceId"));
                sb.append(" VALUES(");
                sb.append("'");
                sb.append(LocalDateTime.now());
                sb.append("',");
                redisData = (List<String>) redisCacheOps.get(CacheKeyBuilder.deviceMode("sb"+jsonObjects.getString("deviceId")));
                // todo 如果redis中没有物模型，则查询数据库，手动新增
                for (int j = 0; j < redisData.size(); j++) {
                    sb.append(jsonObjects.getString(redisData.get(j)));
                    if (j < redisData.size() - 1) {
                        sb.append(",");
                    }
                }
                sb.append("),");
                //拼接数据分析
                jsdata.add(jsonObjects);

                for (int i = 0; i < count; i++) {
                    jsonObjects = (JSONObject) redisCacheOps.rightPop(key);
                    if (jsonObjects != null) {
                        if(redisData!=null){
                            redisData.clear();
                        }
                        sb.append("sb");
                        sb.append(jsonObjects.getString("deviceId"));
                        sb.append(" VALUES(");
                        sb.append("'");
                        sb.append(LocalDateTime.now());
                        sb.append("',");
                        redisData = (List<String>) redisCacheOps.get(CacheKeyBuilder.deviceMode("sb"+jsonObjects.getString("deviceId")));
                        for (int j = 0; j < redisData.size(); j++) {
                            sb.append(jsonObjects.getString(redisData.get(j)));
                            if (j < redisData.size() - 1) {
                                sb.append(",");
                            }
                        }
                        sb.append("),");
                        //拼接数据分析
                        jsdata.add(jsonObjects);
                    }else {
                        break; // 跳出循环
                    }
                }
                if(size>0){
                    // 加入数据库
                    dataAnalysisService.saveTdEngine(sb.toString());
                    //dataAnalysisService.analysis(jsdata);
                }

            }else {
            }
        } catch (Exception e) {
            log.error("数据入库：", e);
        }

    }
}
