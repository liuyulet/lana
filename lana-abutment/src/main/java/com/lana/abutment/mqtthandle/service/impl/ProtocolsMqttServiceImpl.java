package com.lana.abutment.mqtthandle.service.impl;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lana.abutment.mqtthandle.dao.ProtocolsMqttDao;
import com.lana.abutment.mqtthandle.entity.vo.query.ProtocolsMqttQuery;
import com.lana.abutment.mqtthandle.entity.vo.result.ProtocolsMqttResult;
import com.lana.abutment.mqtthandle.convert.ProtocolsMqttConvert;
import com.lana.abutment.mqtthandle.entity.ProtocolsMqttEntity;
import com.lana.abutment.mqtthandle.entity.vo.result.RuleProtocolsMqttResult;
import com.lana.abutment.mqtthandle.entity.vo.save.ProtocolsMqttSave;
import com.lana.abutment.mqtthandle.initializer.cacheproties.DeviceItemModeCacheSave;
import com.lana.abutment.mqtthandle.initializer.proties.ProtocolsMqttProperties;
import com.lana.abutment.mqtthandle.service.ProtocolsMqttService;
import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.RedisCacheOps;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.base.syshandle.enums.GeneralPrefixEnum;
import com.lana.base.syshandle.exception.LanaException;
import com.lana.abutment.mqtthandle.entity.InintProtocolsMqttEntity;
import com.lana.abutment.mqtthandle.entity.vo.update.ProtocolsMqttUpdate;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:33
 */
@Slf4j
@Service
public class ProtocolsMqttServiceImpl extends BaseServiceImpl<ProtocolsMqttDao, ProtocolsMqttEntity> implements ProtocolsMqttService {

    @Resource
    private RedisCacheOps redisCacheOps;

    @Override
    public List<ProtocolsMqttResult> getMqttGroupPage(ProtocolsMqttQuery query) {
        List<ProtocolsMqttResult> page = baseMapper.getMqttGroupPage(query);
        return page;
    }
    @Override
    public List<RuleProtocolsMqttResult> getMqttGroupList(ProtocolsMqttQuery query) {
        List<RuleProtocolsMqttResult> page = baseMapper.getMqttGroupList(query);
        return page;
    }
    @Override
    public void saveProtocolsMqttSave(ProtocolsMqttSave saveVO) {

        if(saveVO.getMqttType()==1){
            long orgCount = count(new QueryWrapper<ProtocolsMqttEntity>().eq("mqtt_type", 1).eq("mqtt_enabled",1));
            if(orgCount>0){
                throw new LanaException("本系统只有一个mqtt-broker，无法进行创建多个");
            }else {
                ProtocolsMqttEntity entity = ProtocolsMqttConvert.INSTANCE.convert(saveVO);
                baseMapper.insert(entity);
            }
        }else {
            ProtocolsMqttEntity entity = ProtocolsMqttConvert.INSTANCE.convert(saveVO);
            baseMapper.insert(entity);
        }
    }

    @Override
    public void updateProtocolsMqtt(ProtocolsMqttUpdate updateVo) {
        if(updateVo.getMqttType()==1){
            long orgCount = count(new QueryWrapper<ProtocolsMqttEntity>().eq("mqtt_type", 1).eq("mqtt_enabled",1));
            if(orgCount>0){
                throw new LanaException("本系统只有一个mqtt-broker，无法进行创建多个");
            }else {
                ProtocolsMqttEntity entity = ProtocolsMqttConvert.INSTANCE.convert(updateVo);
                baseMapper.updateById(entity);
            }
        }else {
            ProtocolsMqttEntity entity = ProtocolsMqttConvert.INSTANCE.convert(updateVo);
            baseMapper.updateById(entity);
        }

    }

    @Override
    public void deleteProtocolsMqtt(Long id) {
        //如果启用状态下，则不允许删除
        ProtocolsMqttEntity protocolsMqttEntity = baseMapper.selectById(id);
        if(protocolsMqttEntity.getMqttEnabled()==1){
            log.info("启用中，不允许删除");
            throw new LanaException("启用中，不允许删除");
        }else {
            baseMapper.deleteById(id);
        }
    }

    @Override
    public void initializeMqtt(ProtocolsMqttProperties protocolsMqttProperties) {
        InintProtocolsMqttEntity protocolsMqttUpdatate = new InintProtocolsMqttEntity();
        ProtocolsMqttEntity protocolsMqttEntity = baseMapper.selectOne(new QueryWrapper<ProtocolsMqttEntity>().eq("mqtt_type", 1).eq("mqtt_enabled",1));
        if(protocolsMqttEntity!=null){
            protocolsMqttUpdatate.setId(protocolsMqttEntity.getId());
            protocolsMqttUpdatate.setIp(protocolsMqttProperties.getIp());
            protocolsMqttUpdatate.setMqttName(protocolsMqttProperties.getName());
            protocolsMqttUpdatate.setMqttType(1);
            protocolsMqttUpdatate.setTcpPort(protocolsMqttProperties.getPort().toString());
            protocolsMqttUpdatate.setWebsocketPort(protocolsMqttProperties.getWebPort().toString());
            protocolsMqttUpdatate.setAuthEnable(protocolsMqttProperties.getAuth().getEnable() ? 1 : 0);
            protocolsMqttUpdatate.setUsername(protocolsMqttProperties.getAuth().getUsername());
            protocolsMqttUpdatate.setPassword(protocolsMqttProperties.getAuth().getPassword());
            protocolsMqttUpdatate.setHttpEnable(protocolsMqttProperties.getHttpEnable() ? 1 : 0);
            protocolsMqttUpdatate.setHttpBasicAuth(protocolsMqttProperties.getHttpBasicAuth().getEnable() ? 1 : 0);
            protocolsMqttUpdatate.setHttpBasicUsername(protocolsMqttProperties.getHttpBasicAuth().getUsername());
            protocolsMqttUpdatate.setHttpBasicPassword(protocolsMqttProperties.getHttpBasicAuth().getPassword());
            protocolsMqttUpdatate.setSslEnabled(protocolsMqttProperties.getSsl().getEnabled() ? 1 : 0);
            protocolsMqttUpdatate.setKeystorePath(protocolsMqttProperties.getSsl().getKeystorePath());
            protocolsMqttUpdatate.setKeystorePass(protocolsMqttProperties.getSsl().getKeystorePass());
            protocolsMqttUpdatate.setClientAuth(protocolsMqttProperties.getSsl().getClientAuth());
            protocolsMqttUpdatate.setTruststorePath(protocolsMqttProperties.getSsl().getTruststorePath());
            protocolsMqttUpdatate.setTruststorePass(protocolsMqttProperties.getSsl().getTruststorePass());
            protocolsMqttUpdatate.setMqttEnabled(protocolsMqttProperties.getEnabled() ? 1 : 0);
            protocolsMqttUpdatate.setLinkType(protocolsMqttProperties.getLinkType());
            protocolsMqttUpdatate.setCreateTime(new DateTime());
            protocolsMqttUpdatate.setUpdateTime(new DateTime());
            protocolsMqttUpdatate.setCreatorName("系统初始化");
            protocolsMqttUpdatate.setUpdaterName("系统初始化");
            baseMapper.updateByProtocol(protocolsMqttUpdatate);
        }else {
            baseMapper.insertProtocol(protocolsMqttUpdatate);
        }

    }

    @Override
    public void initializeCache() {
        List<DeviceItemModeCacheSave> deviceModeListSave = baseMapper.getDevicveeModeList();
        for (DeviceItemModeCacheSave deviceItemModeCacheSave : deviceModeListSave){
            String key = CacheKeyBuilder.deviceMode(GeneralPrefixEnum.TABLE_PREFIX.getValue()+deviceItemModeCacheSave.getDeviceItemId());
            Object data = redisCacheOps.get(key);
            if(data!=null){
                //redisCacheOps.delete(key);
                //redisCacheOps.set(key,deviceItemModeCacheSave.getModeSigns());

            }else {
                redisCacheOps.set(key,deviceItemModeCacheSave.getModeSigns());
            }
        }

    }





/*    @Override
    public void initializeTdengine(ProtocolsMqttProperties protocolsMqttProperties) {
        String data = baseMapper.getTdengineTable("airdetection");
        if(data!=null){
            log.info("TDengine数据库信息不存在，开始建表......");
        }else {
            log.info("TDengine数据库信息已存在，不重复新增......");
            String sql = "CREATE STABLE airdetection (ts timestamp, temperature float, humidity float , pm25  float) TAGS (deviceiD binary(64))";
            baseMapper.initializeTdengine(sql);

        }

    }*/

}
