package com.lana.rules.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.CaffeineCacheManager;
import com.lana.base.operatelog.enums.GeneralPrefixEnum;
import com.lana.rules.dao.DataAnalysisDao;
import com.lana.rules.service.DataAnalysisService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 数据分析
 * @author Liuyulet
 * @version 1.0
 * @data 2024/9/13 13:52
 */
@Slf4j
@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {


    // todo 使用对象池

    @Resource
    private DataAnalysisDao dataAnalysisDao;

    //aviator-path
    @Value("${lana.aviator-path}")
    private String aviatorPath;

    private static List<Map<String, Object>> mapList;


    private static List<String> resultList;

    private static StringBuffer path;

    private static StringBuffer cacheName;

    @Override
    @Async
    public void analysis(List<JSONObject> jsonObject) {
        //todo 待优化：优化数据实时处理方案，走队列，以及提高实时处理性能问题

        // 将 List<JSONObject> 转换为 List<Map<String, Object>>
        mapList.clear();
        resultList.clear();
        path = null;
        cacheName = null;
        for (JSONObject jsonObjects : jsonObject) {
            //从本地缓存中获取脚本
            String key = CacheKeyBuilder.aviatorScript(GeneralPrefixEnum.DEVICE_PREFIX.getValue()+jsonObjects.getString("deviceId"));
            Expression compiledExpression = CaffeineCacheManager.get(GeneralPrefixEnum.DEVICE_PREFIX.getValue(),key);
            if (compiledExpression == null) {
                //如果没有，则从新编译
                Expression exp = null;
                try {
                    String userDir = System.getProperty("user.dir");
                    if(aviatorPath==null || aviatorPath.isEmpty()){
                        String scriptPath = Paths.get(userDir, "lana-rules","src", "main", "resources", "script").toString();
                        path.append(scriptPath).append(GeneralPrefixEnum.DEVICE_TOPIC_PREFIX.getValue()).append(jsonObjects.getString("deviceId")).append(GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue());
                        exp = AviatorEvaluator.getInstance().compileScript(String.valueOf(path),true);//编译脚本文件
                    }else {
                        path.append(aviatorPath).append(GeneralPrefixEnum.DEVICE_TOPIC_PREFIX.getValue()).append(jsonObjects.getString("deviceId")).append(GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue());
                        exp = AviatorEvaluator.getInstance().compileScript(String.valueOf(path),true);//编译脚本文件
                    }
                    //String script = "temperature > 50 ? 'Temperature is too high' : (humidity > 70 ? 'Humidity is too high' : (pm25 > 70 ? 'PM2.5 is too high' : 'All conditions are normal'))";
                    //exp = AviatorEvaluator.compile(script,true); //编译表达式
                    // 将编译后的表达式存入本地缓存
                    cacheName.append(GeneralPrefixEnum.DEVICE_TOPIC_PREFIX.getValue()).append(jsonObjects.getString("deviceId"));
                    CaffeineCacheManager.set(String.valueOf(cacheName), key, exp, 0, TimeUnit.MINUTES);
                    //开始执行
                    Object result = exp.execute(jsonObjects);
                    resultList = (List<String>) result;
                    // todo 动作执行
                    log.info("result:"+resultList);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                //执行逻辑
                try{
                    Object result = compiledExpression.execute(jsonObjects);
                    resultList = (List<String>) result;
                    log.info("result:"+resultList);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void saveTdEngine(String sql) {
        dataAnalysisDao.save(sql);
    }
}
