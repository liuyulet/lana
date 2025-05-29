package com.lana.rules.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.CaffeineCacheManager;
import com.lana.base.cacheops.RedisCacheOps;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.base.syshandle.enums.GeneralPrefixEnum;
import com.lana.base.syshandle.enums.HandleTypeEnum;
import com.lana.base.syshandle.enums.QuartzEnum;
import com.lana.base.syshandle.enums.RuleValueEnum;
import com.lana.base.syshandle.exception.LanaException;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.base.utils.AviatorScriptGeneratorUtils;
import com.lana.rules.convert.RulesItemNodeConvert;
import com.lana.rules.dao.RulesItemDao;
import com.lana.rules.dao.RulesItemNodeDao;
import com.lana.rules.entity.RulesItemEntity;
import com.lana.rules.entity.RulesItemNodeEntity;

import com.lana.rules.entity.RulesItemQuratzEntity;
import com.lana.rules.entity.vo.query.RulesItemNodeQuery;
import com.lana.rules.entity.vo.result.RulesItemNodeResult;
import com.lana.rules.entity.vo.save.RulesItemNodeSave;
import com.lana.rules.service.RulesItemNodeService;
import com.lana.rules.service.RulesItemQuratzService;
import com.lana.rules.service.RulesItemService;
import com.lana.rules.service.SchedulerService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:35
 */
@Slf4j
@Service
public class RulesItemNodeServiceIpml extends BaseServiceImpl<RulesItemNodeDao, RulesItemNodeEntity> implements RulesItemNodeService {

    @Resource
    private SchedulerService schedulerService;
    @Resource
    private RulesItemQuratzService rulesItemQuratzService;
    @Resource
    private RedisCacheOps redisCacheOps;
    @Value("${lana.aviator-path}")
    private String aviatorPath;
    @Resource
    private RulesItemDao rulesItemDao;
    @Resource
    private RulesItemService rulesItemService;






    /**
     * 获取规则详情
     *
     * @param queryVO
     * @return
     */
    @Override
    public RulesItemNodeResult GetRulesItemDetail(RulesItemNodeQuery queryVO) {
        RulesItemNodeEntity rulesItemNodeEntity = baseMapper.selectOne(new QueryWrapper<RulesItemNodeEntity>().eq("rules_id", queryVO.getRulesId()).eq("deleted",0));
        RulesItemNodeResult rulesItemNodeResult = new RulesItemNodeResult();
        rulesItemNodeResult.setNodeConfig(rulesItemNodeEntity.getNodeConfig());
        rulesItemNodeResult.setRulesId(rulesItemNodeEntity.getRulesId());
        rulesItemNodeResult.setName(rulesItemNodeEntity.getName());
        return rulesItemNodeResult;
    }



    /**
     * 新增或者修改规则
     *
     * @param saveRulesItemNodeSaveVO todo 待完善： 处理定时任务/监听设备的规则中，在不添加条件的情况下直接控制动作或者数据转发。
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public LanaResult SaveAndUpdate(RulesItemNodeSave saveRulesItemNodeSaveVO) {
        int handleType = 0;
        try{
            // todo 强制，要求开始节点下只能添加条件
            //查询是否已经有规则了
            RulesItemNodeEntity rulesItemNodeEntity = baseMapper.selectOne(new QueryWrapper<RulesItemNodeEntity>().eq("rules_id", saveRulesItemNodeSaveVO.getRulesId()).eq("deleted",0));
            if(rulesItemNodeEntity!=null){
                //修改规则
                rulesItemNodeEntity.setNodeConfig(saveRulesItemNodeSaveVO.getNodeConfig());
                rulesItemNodeEntity.setName(saveRulesItemNodeSaveVO.getName());
                baseMapper.updateById(rulesItemNodeEntity);
                //开始处理每一个规则要做的动作
                JSONObject jsonObject = JSONObject.parseObject(saveRulesItemNodeSaveVO.getNodeConfig());
                if (jsonObject!=null) {
                    //开始处理每一个规则要做的动作
                    HandleRulesNode(jsonObject, saveRulesItemNodeSaveVO.getRulesId(),HandleTypeEnum.MODIFICATION.getValue());
                }
            }else {
                //新增规则
                RulesItemNodeEntity rulesItemNode = RulesItemNodeConvert.INSTANCE.convert(saveRulesItemNodeSaveVO);
                baseMapper.insert(rulesItemNode);
                //开始处理每一个规则要做的动作
                JSONObject jsonObject = JSONObject.parseObject(saveRulesItemNodeSaveVO.getNodeConfig());
                if (jsonObject!=null) {
                    //开始处理每一个规则要做的动作
                    HandleRulesNode(jsonObject,saveRulesItemNodeSaveVO.getRulesId(),HandleTypeEnum.ADDITIONAL.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            handleType =1;
            throw new LanaException("规则处理失败");
        }finally {
            if (handleType==1){
                return LanaResult.error("规则处理失败,请查看后台日志");
            }else {
                return LanaResult.ok();
            }

        }

    }




    /**
     * 处理规则，并将规则生成为脚本
     * @param jsonObject
     */
    public void HandleRulesNode(JSONObject jsonObject,long rulesId,int HandleType){

        try {
            Long quartzGroup = Instant.now().toEpochMilli();
            //新增规则
            if(HandleType==HandleTypeEnum.ADDITIONAL.getValue()){
                //监听设备
                if(jsonObject.getInteger("setType")== RuleValueEnum.TRIGGERLISTENINGDEVICES.getValue()){
                    // 处理规则
                    GenerateRulesScript(jsonObject,rulesId);
                    //定时任务
                }else if(jsonObject.getInteger("setType")==RuleValueEnum.TRIGGERSCHEDULEDTASKS.getValue()){
                    boolean flag = schedulerService.addCronJob(jsonObject.getString("nodeName")+rulesId,jsonObject.getString("cron"),quartzGroup.toString(),quartzGroup.toString(), QuartzEnum.RULES_CLASS_NAME.getValue());
                    //新增成功
                    if(flag){
                        RulesItemQuratzEntity rulesItemQuratzEntity = getRulesItemQuratzEntity(jsonObject, rulesId,quartzGroup.toString(),quartzGroup.toString());
                        rulesItemQuratzService.addRulesItemQuratz(rulesItemQuratzEntity);
                        // 处理规则
                        GenerateRulesScript(jsonObject,rulesId);
                    }
                }
                //修改规则
            } else if (HandleType==HandleTypeEnum.MODIFICATION.getValue()) {
                // todo 删除规则
                //监听设备
                if (jsonObject.getInteger("setType") == RuleValueEnum.TRIGGERLISTENINGDEVICES.getValue()) {
                    // 处理规则
                    GenerateRulesScript(jsonObject,rulesId);
                    //定时任务
                } else if (jsonObject.getInteger("setType") == RuleValueEnum.TRIGGERSCHEDULEDTASKS.getValue()) {

                    RulesItemQuratzEntity rulesItemQuratzEntity = rulesItemQuratzService.getRulesItemQuratz(rulesId);

                    //删除任务调度
                    if(rulesItemQuratzEntity!=null){
                    schedulerService.deleteCronJob(jsonObject.getString("nodeName") + rulesId, rulesItemQuratzEntity.getJobGroup(), rulesItemQuratzEntity.getTriggerName(), rulesItemQuratzEntity.getTriggerGroup());
                    //删除关联关系
                    rulesItemQuratzService.deleteRulesItemQuratz(rulesId);
                    }

                    //新增任务调度
                    boolean flag = schedulerService.addCronJob(jsonObject.getString("nodeName") + rulesId, jsonObject.getString("cron"),quartzGroup.toString(),quartzGroup.toString(), QuartzEnum.RULES_CLASS_NAME.getValue());
                    //新增定时任务绑定关系
                    if (flag) {
                        RulesItemQuratzEntity rulesItemQuratz = getRulesItemQuratzEntity(jsonObject, rulesId,quartzGroup.toString(),quartzGroup.toString());
                        rulesItemQuratzService.addRulesItemQuratz(rulesItemQuratz);
                        // 处理规则
                        GenerateRulesScript(jsonObject,rulesId);
                    }
                }
            }
        }catch (Exception e){
            throw new LanaException("规则生成为脚本处理失败");
        }


    }

    /**
     * 获取规则定时任务信息,用于建立关联关系
     * @param jsonObject
     * @param rulesId
     * @return
     */
    public RulesItemQuratzEntity getRulesItemQuratzEntity(JSONObject jsonObject,long rulesId,String jobGroup,String triggerGroup){
        RulesItemQuratzEntity rulesItemQuratzEntity = new RulesItemQuratzEntity();
        rulesItemQuratzEntity.setRulesId(rulesId);
        rulesItemQuratzEntity.setCron(jsonObject.getString("cron"));
        rulesItemQuratzEntity.setJobGroup(jobGroup);
        rulesItemQuratzEntity.setTriggerName(QuartzEnum.TRIGGER_PRE.getValue() + jsonObject.getString("nodeName")+rulesId);
        rulesItemQuratzEntity.setTriggerGroup(triggerGroup);
        return rulesItemQuratzEntity;
    }

    /**
     * 生成规则脚本
     * @param jsonObject
     */
    public void GenerateRulesScript(JSONObject jsonObject,long rulesId) throws Exception {

        baseMapper.delRulesJobDeviceId(rulesId);
        baseMapper.delRulesJobPushDevice(rulesId);

        String rulesJobPushDeviceKey = CacheKeyBuilder.rulesJobPushDevice(GeneralPrefixEnum.RULE_PREFIX.getValue() + rulesId);
        Object rulesJobPushDeviceData = redisCacheOps.get(rulesJobPushDeviceKey);
        if (rulesJobPushDeviceData != null) {
            redisCacheOps.delete(rulesJobPushDeviceKey);
        }

        List<Map<String, Object>> ruleDataList = new ArrayList<>();
        //开始端=监听设备
        if(jsonObject.getInteger("setType")==RuleValueEnum.TRIGGERLISTENINGDEVICES.getValue()){
            //1、将监听的设备与规则对应的关系存入缓存中
            if(!jsonObject.getJSONArray("nodeDeviceList").isEmpty()){
                JSONArray nodeDeviceList = jsonObject.getJSONArray("nodeDeviceList");
                //先存储要监听的设备
                Set<BigInteger> timeDeviceIdList = new HashSet<BigInteger>();
                for (int i = 0; i < nodeDeviceList.size(); i++) {
                    //监听条件中，只能选择开始中已经选择的监听设备，不然不起作用
                    BigInteger id = nodeDeviceList.getJSONObject(i).getBigInteger("id");
                    timeDeviceIdList.add(id);
                    //设备--规则关联关系
                    String key = CacheKeyBuilder.deviceIdRule(GeneralPrefixEnum.TABLE_PREFIX.getValue()+id);
                    Object data = redisCacheOps.get(key);
                    if(data!=null){
                        redisCacheOps.delete(key);
                        redisCacheOps.set(key,rulesId);
                    }else {
                        redisCacheOps.set(key,rulesId);
                    }
                }
                //更新监听设备中的，设备所属规则信息
                baseMapper.delRulesListenDeviceId(rulesId);
                baseMapper.addRulesListenDeviceId(timeDeviceIdList,rulesId);


                if (!jsonObject.getJSONObject("childNode").isEmpty()) {
                    JSONObject childNodeData = jsonObject.getJSONObject("childNode");
                    ruleDataList = HandleConditionNodesList(childNodeData,rulesId,RuleValueEnum.TRIGGERLISTENINGDEVICES.getValue());
                }
            }

        //开始端=定时触发
        }else if(jsonObject.getInteger("setType")==RuleValueEnum.TRIGGERSCHEDULEDTASKS.getValue()) {
            //将条件中的设备与规则对应的关系加入到缓存中
            if (!jsonObject.getJSONObject("childNode").isEmpty()) {
                JSONObject childNodeData = jsonObject.getJSONObject("childNode");
                ruleDataList = HandleConditionNodesList(childNodeData,rulesId,RuleValueEnum.TRIGGERSCHEDULEDTASKS.getValue());
                //定时任务要存储定时采集的设备信息（每个要对比的条件，就是要采集的设备）
            }
        }

        log.info("规则数据：{}",ruleDataList);
        //将数据以map的方式传入脚本生成器中
        Map<String, Object> list = new HashMap<>();
        list.put("list", ruleDataList);
        String script = AviatorScriptGeneratorUtils.getAviatorScriptGenerator(list);
        if(script!=null){
            //log.info("script数据：{}",script);
            //生成文件并进行存储，方便后续的增删改查
            updataAndCreateFile(script,rulesId);
        }

    }





    /**
     * 规则条件处理
     * @param childNodeData
     * @param rulesId
     * @param setType
     */
    // todo 这块后期优化一下，提高整体的代码质量。
    public List<Map<String, Object>> HandleConditionNodesList(JSONObject childNodeData,long rulesId,int setType) throws Exception {

        log.info("开始删除-----------------------------------------------------");
        //删除
        List<String> acCodeList = baseMapper.getacCodes(rulesId);
        for (String skey : acCodeList){
            Object data = redisCacheOps.get(skey);
            //入缓存
            if (data != null) {
                redisCacheOps.delete(skey);
            }
        }
        //删除
        baseMapper.deleteAc(rulesId);
        RulesItemEntity rulesItemEntity = rulesItemService.getById(rulesId);
        List<Map<String, Object>> ruleDataList = new ArrayList<>();
        if (!childNodeData.isEmpty()) {

            // 缓存规则类型，用于后期的规则判断
            CaffeineCacheManager.deleteKey("RulesType",  rulesId);
            CaffeineCacheManager.set("RulesType",  rulesId, childNodeData.getInteger("type"), 0, TimeUnit.MINUTES);


            //type==4条件
            if (childNodeData.getInteger("type") ==RuleValueEnum.NODECONDITIONAL.getValue()) {

                if (childNodeData.containsKey("conditionNodes")) {
                    if (!childNodeData.getJSONArray("conditionNodes").isEmpty()) {
                        JSONArray conditionNodesList = childNodeData.getJSONArray("conditionNodes");
                        for (int i = 0; i < conditionNodesList.size(); i++) {

                            //与、或判断
                            Integer conditionMode = conditionNodesList.getJSONObject(i).getInteger("conditionMode");

                            Map<String, Object> conditionMap = new HashMap<>();
                            //数据集合,生成脚本使用
                            List<Map<String, Object>> listData = new ArrayList<>();

                            //定时任务，设备id集合
                            Set<BigInteger> deviceIdList = new HashSet<BigInteger>();

                            //条件处理
                            if (conditionNodesList.getJSONObject(i).containsKey("childNode")) {
                                JSONArray conditionList = conditionNodesList.getJSONObject(i).getJSONArray("conditionList");
                                if (!conditionList.isEmpty()) {
                                    //处理条件
                                    for (int j = 0; j < conditionList.size(); j++) {
                                        //数据内容，生成脚本使用
                                        Map<String, Object> conditionMapData = new HashMap<>();
                                        BigInteger deviceId = conditionList.getJSONObject(j).getBigInteger("id");

                                        // todo 考虑将定时规则与设备之间的关联关系实例化
                                        if (setType == RuleValueEnum.TRIGGERSCHEDULEDTASKS.getValue()) {
                                            //设备--定时规则关联关系（采集规则），这个设备所属的定时规则
                                            String key = CacheKeyBuilder.deviceIdRuleJon(GeneralPrefixEnum.TABLE_PREFIX.getValue() + deviceId);
                                            Object data = redisCacheOps.get(key);
                                            if (data != null) {
                                                redisCacheOps.delete(key);
                                                redisCacheOps.set(key, rulesId);
                                            } else {
                                                redisCacheOps.set(key, rulesId);
                                            }
                                        }

                                        //将每个调教后面的动作也一起存进去，在规则生成的时候将匹配后的数据直接返回出来，并根据返回的动作信息进行对应的动作操作
                                        conditionMapData.put("modeSigns", conditionList.getJSONObject(j).getString("modeSigns"));     // 物模型字段值
                                        conditionMapData.put("operator", conditionList.getJSONObject(j).getString("operator"));  // 比较符号，使用 "gt" 表示 ">"
                                        conditionMapData.put("threshold", conditionList.getJSONObject(j).getString("value"));    // 阈值
                                        conditionMapData.put("modeSignsName", conditionList.getJSONObject(j).getString("modeSignsName"));   // 物模型描述信息
                                        conditionMapData.put("deviceName", conditionList.getJSONObject(j).getString("label"));   // 设备名称
                                        conditionMapData.put("deviceId", deviceId);   // 设备值
                                        deviceIdList.add(deviceId);
                                        //创建条件
                                        listData.add(conditionMapData);
                                    }
                                }
                            }
                            if (setType == RuleValueEnum.TRIGGERSCHEDULEDTASKS.getValue()) {
                                String deviceIds = "";
                                if (deviceIdList != null) {
                                    for (BigInteger bigInteger : deviceIdList){
                                        deviceIds += bigInteger + ",";
                                    }
                                    //将条件中的设备加入到数据库中(与之对应的redis缓存为：deviceIdTuleJob)
                                    baseMapper.addRulesJobDeviceId(deviceIdList,rulesId);

                                    //用于缓存定时任务中，达到时间之后主动下发采集指令的设备Id
                                    baseMapper.addRulesJobPushDevice(deviceIds.substring(0, deviceIds.length() - 1),rulesId);
                                    String rulesJobPushDeviceKey = CacheKeyBuilder.rulesJobPushDevice(GeneralPrefixEnum.RULE_PREFIX.getValue() + rulesId);
                                    Object rulesJobPushDeviceData = redisCacheOps.get(rulesJobPushDeviceKey);
                                    if (rulesJobPushDeviceData != null) {
                                        String rulesJobPushDeviceId = (String)rulesJobPushDeviceData;
                                        redisCacheOps.set(rulesJobPushDeviceKey, rulesJobPushDeviceId+","+deviceIds.substring(0, deviceIds.length() - 1));
                                    }else {
                                        redisCacheOps.set(rulesJobPushDeviceKey,deviceIds.substring(0, deviceIds.length() - 1));
                                    }

                                }
                            }
                            //加入条件集合
                            conditionMap.put("conditionList", listData);
                            //条件后面是否有动作，如果有动作就执行动作
                            if (conditionNodesList.getJSONObject(i).containsKey("childNode")) {
                                if (!conditionNodesList.getJSONObject(i).getJSONObject("childNode").isEmpty()) {
                                    JSONObject actionChildNode = conditionNodesList.getJSONObject(i).getJSONObject("childNode");

                                    //条件数据集合
                                    Map<String, Object> actionMapData = new HashMap<>();


                                    //控制设备
                                    if (actionChildNode.getInteger("type") == RuleValueEnum.NODEDEVICECONTROL.getValue()) {

                                        actionMapData.put("userId", rulesItemEntity.getCreator());
                                        actionMapData.put("userName", rulesItemEntity.getCreatorName());

                                        //动作类型：1设备控制，2数据转发
                                        actionMapData.put("actionType", actionChildNode.getInteger("type"));//设备控制
                                        //控制设备类型--1指定设备/2指定分组
                                        actionMapData.put("controlType", actionChildNode.getInteger("setType"));//设备控制
                                        //顺序执行，1:按顺序依次执行;2:不按顺序同步执行
                                        actionMapData.put("seqExecute", actionChildNode.getInteger("selectMode"));//多设备时控制执行方式--按顺序依次执行
                                        //超时执行
                                        actionMapData.put("timeoutExecution", actionChildNode.getInteger("termMode"));//期限超时后执行（0：自动通过，1：自动停止）
                                        //控制执行动作类型
                                        actionMapData.put("controlActionType", actionChildNode.getInteger("actionType"));//1控制已选设备开、2控制已选设备关、3自定义控制设备
                                        //控制执行动作--自定义控制设备列表
                                        actionMapData.put("deviceList", actionChildNode.getJSONArray("nodeDeviceList"));
                                        //设备分组
                                        actionMapData.put("controlGroupId", actionChildNode.getJSONArray("nodeGroupList"));//设备控制

                                        if (actionChildNode.containsKey("childNode")) {
                                            if (!actionChildNode.getJSONObject("childNode").isEmpty()) {
                                                JSONObject sendJson = actionChildNode.getJSONObject("childNode");
                                                //todo 一般一个条件下仅支持一个动作，所以在保存的时候，如果出现了多个动作，则是有问题的规则
                                                if (sendJson.getInteger("type") == RuleValueEnum.NODEDEVICECONTROL.getValue()) {
                                                    throw new LanaException("目前版本一个条件下仅支持一个动作哦");
                                                    //数据流转
                                                } else if (sendJson.getInteger("type") == RuleValueEnum.NODEDATAPORTERING.getValue()) {
                                                    //数据流转
                                                    actionMapData.put("pushData", sendJson.getString("pushData"));//数据流转
                                                    //推送数据类型,1:设备预警消息推送，2：接触预警数据推送
                                                    actionMapData.put("sendType", sendJson.getInteger("sendType"));//推送数据类型
                                                    //推送数据名称
                                                    actionMapData.put("nodeName", sendJson.getString("nodeName"));//推送数据名称
                                                    //推送数据topic
                                                    actionMapData.put("pushTopic", sendJson.getString("pushTopic"));//推送数据topic
                                                    //推送数据到哪个平台
                                                    actionMapData.put("pushPlatformInformation", sendJson.getJSONArray("pushPlatformInformation"));//推送数据到哪个平台

                                                }
                                            }
                                        }
                                        //conditionMap.put("actionMap",actionMapData);
                                    } else if (actionChildNode.getInteger("type") == RuleValueEnum.NODEDATAPORTERING.getValue()) {

                                        actionMapData.put("userId", rulesItemEntity.getCreator());
                                        actionMapData.put("userName", rulesItemEntity.getCreatorName());

                                        //将动作处理
                                        //数据流转
                                        actionMapData.put("pushData", actionChildNode.getString("pushData"));//数据流转
                                        //推送数据类型,1:设备预警消息推送，2：接触预警数据推送
                                        actionMapData.put("sendType", actionChildNode.getInteger("sendType"));//推送数据类型
                                        //推送数据名称
                                        actionMapData.put("nodeName", actionChildNode.getString("nodeName"));//推送数据名称
                                        //推送数据topic
                                        actionMapData.put("pushTopic", actionChildNode.getString("pushTopic"));//推送数据topic
                                        //推送数据到哪个平台
                                        actionMapData.put("pushPlatformInformation", actionChildNode.getJSONArray("pushPlatformInformation"));//推送数据到哪个平台
                                    }

                                    long epochMilli = Instant.now().toEpochMilli();
                                    //设备--定时规则关联关系（采集规则），这个设备所属的定时规则
                                    String key = CacheKeyBuilder.actionMap(epochMilli);
                                    //以json的方式进行持久化

                                    String json = JSON.toJSONString(actionMapData, SerializerFeature.WriteClassName);
                                    baseMapper.addAc(key, json, rulesId);
                                    //缓存数据
                                    CaffeineCacheManager.deleteKey("RulesActionMap",  rulesId);
                                    CaffeineCacheManager.set("RulesActionMap",  rulesId, key, 0, TimeUnit.MINUTES);

                                    Object data = redisCacheOps.get(key);
                                    //入缓存
                                    if (data != null) {
                                        redisCacheOps.delete(key);
                                        redisCacheOps.set(key, actionMapData);
                                    } else {
                                        redisCacheOps.set(key, actionMapData);
                                    }
                                    conditionMap.put("actionMap", key);
                                    conditionMap.put("conditionMode", conditionMode);
                                }
                            }
                            ruleDataList.add(conditionMap);
                        }
                    }
                }
            }
            //type==1设备控制
            if (childNodeData.getInteger("type") ==RuleValueEnum.NODEDEVICECONTROL.getValue()){
                //条件数据集合
                Map<String, Object> actionMapData = new HashMap<>();
                actionMapData.put("userId", rulesItemEntity.getCreator());
                actionMapData.put("userName", rulesItemEntity.getCreatorName());
                //动作类型：1设备控制，2数据转发
                actionMapData.put("actionType", childNodeData.getInteger("type"));//设备控制
                //控制设备类型--1指定设备/2指定分组
                actionMapData.put("controlType", childNodeData.getInteger("setType"));//设备控制
                //顺序执行，1:按顺序依次执行;2:不按顺序同步执行
                actionMapData.put("seqExecute", childNodeData.getInteger("selectMode"));//多设备时控制执行方式--按顺序依次执行
                //超时执行
                actionMapData.put("timeoutExecution", childNodeData.getInteger("termMode"));//期限超时后执行（0：自动通过，1：自动停止）
                //控制执行动作类型
                actionMapData.put("controlActionType", childNodeData.getInteger("actionType"));//1控制已选设备开、2控制已选设备关、3自定义控制设备
                //控制执行动作--自定义控制设备列表
                actionMapData.put("deviceList", childNodeData.getJSONArray("nodeDeviceList"));
                //设备分组
                actionMapData.put("controlGroupId", childNodeData.getJSONArray("nodeGroupList"));//设备控制

                if (childNodeData.containsKey("childNode")) {
                    if (!childNodeData.getJSONObject("childNode").isEmpty()) {
                        JSONObject sendJson = childNodeData.getJSONObject("childNode");
                        //todo 一般一个条件下仅支持一个动作，所以在保存的时候，如果出现了多个动作，则是有问题的规则
                        if (sendJson.getInteger("type") == RuleValueEnum.NODEDEVICECONTROL.getValue()) {
                            throw new LanaException("目前版本一个条件下仅支持一个动作哦");
                            //数据流转
                        } else if (sendJson.getInteger("type") == RuleValueEnum.NODEDATAPORTERING.getValue()) {
                            //数据流转
                            actionMapData.put("pushData", sendJson.getString("pushData"));//数据流转
                            //推送数据类型,1:设备预警消息推送，2：接触预警数据推送
                            actionMapData.put("sendType", sendJson.getInteger("sendType"));//推送数据类型
                            //推送数据名称
                            actionMapData.put("nodeName", sendJson.getString("nodeName"));//推送数据名称
                            //推送数据topic
                            actionMapData.put("pushTopic", sendJson.getString("pushTopic"));//推送数据topic
                            //推送数据到哪个平台
                            actionMapData.put("pushPlatformInformation", sendJson.getJSONArray("pushPlatformInformation"));//推送数据到哪个平台

                        }
                    }
                }

                long epochMilli = Instant.now().toEpochMilli();
                //设备--定时规则关联关系（采集规则），这个设备所属的定时规则
                String key = CacheKeyBuilder.actionMap(epochMilli);
                //以json的方式进行持久化
                String json = JSON.toJSONString(actionMapData, SerializerFeature.WriteClassName);
                baseMapper.addAc(key, json, rulesId);
                Object data = redisCacheOps.get(key);
                //入缓存
                if (data != null) {
                    redisCacheOps.delete(key);
                    redisCacheOps.set(key, actionMapData);
                } else {
                    redisCacheOps.set(key, actionMapData);
                }
            }
            //type==2数据流转
            if (childNodeData.getInteger("type") ==RuleValueEnum.NODEDATAPORTERING.getValue()){
                Map<String, Object> actionMapData = new HashMap<>();
                //数据流转
                actionMapData.put("pushData", childNodeData.getString("pushData"));//数据流转
                //推送数据类型,1:设备预警消息推送，2：接触预警数据推送
                actionMapData.put("sendType", childNodeData.getInteger("sendType"));//推送数据类型
                //推送数据名称
                actionMapData.put("nodeName", childNodeData.getString("nodeName"));//推送数据名称
                //推送数据topic
                actionMapData.put("pushTopic", childNodeData.getString("pushTopic"));//推送数据topic
                //推送数据到哪个平台
                actionMapData.put("pushPlatformInformation", childNodeData.getJSONArray("pushPlatformInformation"));//推送数据到哪个平台
                long epochMilli = Instant.now().toEpochMilli();
                //设备--定时规则关联关系（采集规则），这个设备所属的定时规则
                String key = CacheKeyBuilder.actionMap(epochMilli);
                //以json的方式进行持久化
                String json = JSON.toJSONString(actionMapData, SerializerFeature.WriteClassName);
                baseMapper.addAc(key, json, rulesId);
                Object data = redisCacheOps.get(key);
                //入缓存
                if (data != null) {
                    redisCacheOps.delete(key);
                    redisCacheOps.set(key, actionMapData);
                } else {
                    redisCacheOps.set(key, actionMapData);
                }
            }
        }

        rulesItemEntity.setRuleType(childNodeData.getInteger("type"));
        rulesItemDao.updateById(rulesItemEntity);


        return ruleDataList;
    }

    /**
     * 生成规则文件
     * 内容
     */
    public Boolean updataAndCreateFile(String script,long rulesId) throws IOException {

            RulesItemEntity rulesItemEntity = rulesItemDao.selectById(rulesId);
            //脚本内容
            rulesItemEntity.setAsContent(script);
            if(rulesItemEntity.getAsPath() !=null && !rulesItemEntity.getAsPath().isEmpty()){
                //保存规则
                Path path = Paths.get(rulesItemEntity.getAsPath());
                Path parentPath = path.getParent();
                // 生成新的文件名
                String newFileName = rulesId + GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue();
                // 拼接完整的文件路径
                Path newFilePath = parentPath.resolve(newFileName);
                // 将父目录和新文件名转换回字符串
                String newContentPath = newFilePath.toString();
                //操作文件
                boolean isSuccess = deleteAndCreateFile(rulesItemEntity.getAsPath(),newContentPath,rulesItemEntity.getAsContent());
                //脚本路径
                rulesItemEntity.setAsPath(newContentPath);

                if(isSuccess){
                    //修改数据库
                    rulesItemDao.updateById(rulesItemEntity);
                }
                //处理缓存中
                Expression exp = AviatorEvaluator.getInstance().compileScript(String.valueOf(rulesItemEntity.getAsPath()),true);//编译脚本文件
                CaffeineCacheManager.deleteKey("AviatorScript",  rulesId + GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue());
                CaffeineCacheManager.set("AviatorScript",  rulesId + GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue(), exp, 0, TimeUnit.MINUTES);
                log.info("========================= Aviatorscript:RULES{} =========================初始化成功",rulesId + GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue());
                return true;
            }else {
                rulesItemEntity.setAsContent(script);
                //保存文件
                String filePath = saveRulesFile(rulesItemEntity,rulesId);
                rulesItemEntity.setAsPath(filePath);
                rulesItemDao.updateById(rulesItemEntity);

                //处理缓存中
                Expression exp = AviatorEvaluator.getInstance().compileScript(String.valueOf(rulesItemEntity.getAsPath()),true);//编译脚本文件
                CaffeineCacheManager.set("AviatorScript",  rulesId + GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue(), exp, 0, TimeUnit.MINUTES);
                log.info("========================= Aviatorscript:RULES{} =========================初始化成功",rulesId + GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue());
                return true;
            }

    }

     /**
     * 新增脚本文件
     * @param rulesItemEntity
     */
    public String saveRulesFile(RulesItemEntity rulesItemEntity,long rulesId) {
        String targetDirectory = null;
        if(aviatorPath==null || aviatorPath.isEmpty()){
            String userDir = System.getProperty("user.dir");
            String scriptPath = Paths.get(userDir, "lana-rules","src", "main", "resources", "script").toString();
            targetDirectory = scriptPath;
        }else {
            targetDirectory = aviatorPath;
        }
        String fileName =  rulesId+GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue();
        Path filePath = Paths.get(targetDirectory, fileName);
        try {
            // 创建目录（如果不存在）
            Files.createDirectories(filePath.getParent());
            // 创建文件并写入内容
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()))) {
                writer.write(rulesItemEntity.getAsContent());
                System.out.println("文件创建并写入成功");
                //返回文件路径
                return filePath.toString();
            }
        } catch (IOException e) {
            System.err.println("文件创建或写入失败: " + e.getMessage());
            //返回文件路径
            throw new LanaException("文件创建或写入失败");

        }
    }

    /**
     * 修改规则脚本文件
     * @param oldFilePath
     * @param newContentPath
     * @param newContent
     * @return
     */

    public boolean deleteAndCreateFile(String oldFilePath, String newContentPath, String newContent) {

        Path oldFile = Paths.get(oldFilePath);
        Path newFile = Paths.get(newContentPath);

        try {
            // 删除旧文件
            if (Files.exists(oldFile)) {
                Files.delete(oldFile);
                log.info("旧文件 " + oldFilePath + " 已经被删除");
            }

            // 创建新文件
            Files.createFile(newFile);
            try (BufferedWriter writer = Files.newBufferedWriter(newFile)) {
                writer.write(newContent);
                log.info("新文件 " + newContentPath + " 已经创建并写入成功");
            }
            return true;
        } catch (IOException e) {
            log.error("文件操作失败: " + e.getMessage());
            return false;
        }
    }



}
