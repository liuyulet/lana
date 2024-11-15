package com.lana.device.alarming;

import com.alibaba.fastjson.JSONObject;
import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.CacheOps;
import com.lana.rules.service.DataAnalysisService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/9/13 14:05
 */
@Slf4j
@Component
public class DataProcessing {
    @Value("${lana.mqtt-data-queue:20}")
    private int dataNum;
    @Resource
    private CacheOps cacheOps;
    @Resource
    private DataAnalysisService dataAnalysisService;

    private final StringBuilder sb = new StringBuilder();

    private final List<JSONObject> jsdata = new ArrayList<>();

    private List<String> redisData = new ArrayList<>();

    private JSONObject jsonObjects = new JSONObject();


    @PostConstruct
    @Scheduled(fixedDelay = 10000)
    public void saveJsonToDb() {
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
            jsonObjects = (JSONObject) cacheOps.rightPop(key);
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
                redisData = (List<String>)cacheOps.get(CacheKeyBuilder.deviceMode("sb"+jsonObjects.getString("deviceId")));
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
                    jsonObjects = (JSONObject) cacheOps.rightPop(key);
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
                        redisData = (List<String>)cacheOps.get(CacheKeyBuilder.deviceMode("sb"+jsonObjects.getString("deviceId")));
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
                    dataAnalysisService.analysis(jsdata);
                }

            }else {
            }
        } catch (Exception e) {
            log.error("数据分析错误，请查看日志：", e);
        }
    }

}
