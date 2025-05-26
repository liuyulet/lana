package com.lana.rules.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.mybatis.dao.BaseDao;
import com.lana.base.syshandle.entity.DeviceControl;
import com.lana.rules.entity.*;
import com.lana.rules.entity.vo.query.RulesItemPageQuery;
import com.lana.rules.entity.vo.query.RulesItemQuery;
import com.lana.rules.entity.vo.result.RulesItemResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RulesItemDao extends BaseDao<RulesItemEntity> {
    //RulesItemResult getRules(@Param("queryVO") RulesItemQuery queryVO);


    IPage<RulesItemResult> getRulesPage(@Param("page") IPage<RulesItemEntity> page,@Param("model")  RulesItemPageQuery query, @Param("DataScopeIgnore") boolean DataScopeIgnore);

    Integer getRulesItem(@Param("ruleId") Long id);


    void deleteRulesItemNode(@Param("ruleId") Long id);

    List<RulesListenDeviceIdEntity> getRulesListenDeviceIdList();

    List<RulesJobDeviceIdEntity> getRulesJobDeviceId();

    List<RulesJobPushDeviceEntity> getRulesJobPushDevice();

    List<Long> getDeviceItemIds();

    ArrayList<DeviceControl> getDeviceControlList(Long deviceItemId);

    List<Long> getDeviceModes();

    ArrayList<String> getDeviceModeList(Long deviceId);

    List<RulesActionInstancEntity> getActionMapData();

    List<RulesActionInstancEntity> getUnconditionalCache();
}
