package com.lana.rules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.rules.dao.RulesItemQuratzDao;
import com.lana.rules.entity.RulesItemQuratzEntity;
import com.lana.rules.service.RulesItemQuratzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liuyulet
 * @create 2025/3/4 16:08
 */
@Slf4j
@Service
public class RulesItemQuratzServiceImpl extends BaseServiceImpl<RulesItemQuratzDao, RulesItemQuratzEntity> implements RulesItemQuratzService {
    @Override
    public void addRulesItemQuratz(RulesItemQuratzEntity rulesItemQuratzEntity) {
        baseMapper.insert(rulesItemQuratzEntity);
    }

    @Override
    public void deleteRulesItemQuratz(long rulesId) {
        baseMapper.deleteRulesItemQuratz(rulesId);
    }

    @Override
    public RulesItemQuratzEntity getRulesItemQuratz(long rulesId) {
        RulesItemQuratzEntity rulesItemQuratzEntity = baseMapper.selectOne(new QueryWrapper<RulesItemQuratzEntity>()
                .eq("rules_id", rulesId)
                .eq("deleted", 0)
        );
        return rulesItemQuratzEntity;
    }

    @Override
    public Long getRuleJobPushDevice(String name) {
        return baseMapper.getRuleJobPushDevice(name);
    }
}
