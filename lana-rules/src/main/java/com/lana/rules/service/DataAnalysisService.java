package com.lana.rules.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/9/13 13:56
 */

public interface DataAnalysisService {
    void analysis(List<JSONObject> jsonObject);

    void saveTdEngine(String sql);
}
