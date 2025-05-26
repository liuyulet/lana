package com.lana.rules.dao;

import com.lana.base.mybatis.dao.BaseDao;
import com.lana.rules.entity.RulesItemQuratzEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liuyulet
 * @create 2025/3/4 16:09
 */
@Mapper
public interface RulesItemQuratzDao extends BaseDao<RulesItemQuratzEntity> {

    void deleteRulesItemQuratz(long rulesId);

    Long getRuleJobPushDevice(String name);
}
