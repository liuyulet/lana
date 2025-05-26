package com.lana.rules.service;


import com.lana.base.mybatis.service.BaseService;
import com.lana.rules.entity.RulesItemQuratzEntity;


/**
 * @auther liuyulet
 * @date 2024/9/13 21:30
 */
public interface RulesItemQuratzService extends BaseService<RulesItemQuratzEntity> {

    void addRulesItemQuratz(RulesItemQuratzEntity rulesItemQuratzEntity);

    void deleteRulesItemQuratz(long rulesId);

    RulesItemQuratzEntity getRulesItemQuratz(long rulesId);

    Long getRuleJobPushDevice(String name);
}
