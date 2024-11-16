package com.lana.rules.dao;

import com.lana.base.mybatis.dao.BaseDao;
import com.lana.rules.entity.RulesItemEntity;
import com.lana.rules.entity.vo.query.RulesItemQuery;
import com.lana.rules.entity.vo.result.RulesItemResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RulesItemDao extends BaseDao<RulesItemEntity> {
    RulesItemResult getRules(@Param("queryVO") RulesItemQuery queryVO);

    void saveRulesDeviceItem(@Param("rulesId") Long rulesId,@Param("deviceItemId") Long deviceItemId);
}
