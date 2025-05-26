package com.lana.rules.dao;

import com.lana.base.mybatis.dao.BaseDao;
import com.lana.rules.entity.RulesItemNodeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Mapper
public interface RulesItemNodeDao extends BaseDao<RulesItemNodeEntity> {

    void addAc(@Param("acCode") String key,@Param("acInstancing") String actionMapData,@Param("ruleId") long ruleId);

    void deleteAc(@Param("rulesId")  long rulesId);

    List<String> getacCodes(@Param("rulesId") long rulesId);

    void addRulesJobDeviceId(@Param("deviceIdList") Set<BigInteger> deviceIdList ,@Param("rulesId") long rulesId);

    void delRulesJobDeviceId(long rulesId);

    void delRulesJobPushDevice(long rulesId);

    void addRulesJobPushDevice(@Param("deviceIds") String deviceIds, @Param("rulesId") long rulesId);

    void delRulesListenDeviceId(long rulesId);

    void addRulesListenDeviceId(@Param("timeDeviceIdList") Set<BigInteger> timeDeviceIdList,@Param("rulesId") long rulesId);
}
