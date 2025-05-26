package com.lana.rules.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.CaffeineCacheManager;
import com.lana.base.syshandle.enums.GeneralPrefixEnum;
import com.lana.rules.dao.DataAnalysisDao;
import com.lana.rules.entity.DeviceControlEntity;
import com.lana.rules.service.DataAnalysisService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.file.Paths;
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



    @Resource
    private DataAnalysisDao dataAnalysisDao;


    @Override
    public void saveTdEngine(String sql) {
        dataAnalysisDao.save(sql);
    }

    @Override
    public List<DeviceControlEntity> getDeviceControlList(List<BigInteger> longs) {
        return dataAnalysisDao.getDeviceControlList(longs);
    }
}
