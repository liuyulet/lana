package com.lana.rules.executes.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lana.abutment.mqtthandle.servepublish.MessagePublish;
import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.RedisCacheOps;
import com.lana.base.operatelog.entity.OptLogEntity;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.security.token.user.SecurityUser;
import com.lana.base.syshandle.enums.GeneralPrefixEnum;
import com.lana.base.utils.JsonUtils;
import com.lana.rules.entity.DeviceControlEntity;
import com.lana.rules.service.DataAnalysisService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 规则作处理程序
 * @author liuyulet
 * @create 2025/5/19 16:35
 */
@Slf4j
@Component
public class RulesActionHandler {

    @Resource
    private RedisCacheOps redisCacheOps;

    @Resource
    private DataForwardHandler dataForwardHandler;

    @Resource
    private DeviceControlHandler deviceControlHandler;

    //mqtt数据转发，一般是基于topic进行转发，包括设备的控制，也会基于mqtt下发指令
    @Resource
    private MqttMessageForwarder mqttMessageForwarder;

    @Resource
    private DataAnalysisService dataAnalysisService;
    @Resource
    private MessagePublish messagePublish;



    /**
     * todo 后续这里要进行全方面的优化，目前以满足需求为主
     * 动作处理
     * @param actionMap
     *
     *
     * 动作：
     * actionType（动作类型：1设备控制，2数据转发）
     * controlType（控制设备类型--1指定设备，2指定分组,设备列表中的除外）
     * seqExecute（顺序执行，1:按顺序依次执行;2:不按顺序同步执行）
     * timeoutExecution（期限超时后执行0：自动通过，1：自动停止）
     * controlActionType（1控制已选设备开、2控制已选设备关、3自定义控制设备）
     * deviceList（控制执行动作--自定义控制设备列表）
     * controlGroupId（控制执行动作--设备分组）
     *
     * 推送：
     * pushData（数据流转）
     * sendType（推送数据类型,1:设备预警消息推送，2：接触预警数据推送）
     * nodeName（推送数据名称）
     * pushTopic（推送数据topic）
     * pushPlatformInformation（推送数据到哪个平台）
     *
     */
    public void handle(Map<String, Object> actionMap) {
        Object data = redisCacheOps.get(actionMap.get("actionMap").toString());

        log.info("命中规则：{}",actionMap.get("message").toString());

        log.info("开始处理动作：{}",actionMap.get("actionMap").toString());

        StringBuffer allLog = new StringBuffer();
        //入缓存
        if (data != null) {
            Map<String, Object> actionMapData = (Map<String, Object>) data;

            // todo 后续在考虑要不要使用条件命中的记录
/*            optLog(Long.parseLong(actionMapData.get("userId").toString()),actionMapData.get("userName").toString(),
                    "设备告警(条件命中)",
                    "命中规则："+actionMap.get("message").toString(),
                    OperateTypeEnum.WARNINGMESSAGES.getValue());*/
            allLog.append("设备数据命中规则：");
            allLog.append(actionMap.get("message").toString());
            allLog.append("；");

            //1控制已选设备开、2控制已选设备关、3自定义控制设备
            if(actionMapData.containsKey("controlActionType")){
                if(actionMapData.get("controlActionType").equals(3)){

                    JSONArray deviceArray = (JSONArray)actionMapData.get("deviceList");
                    List<BigInteger> abilityLists = new ArrayList<>();
                    for (int i = 0; i < deviceArray.size(); i++) {
                        //这里如果追求性能可以将动作从redis缓存中处理。我这里进行查数据库处理。
                        abilityLists.add(deviceArray.getJSONObject(i).getBigInteger("controlAbility"));
                    }
                    List<DeviceControlEntity> DeviceControlList = dataAnalysisService.getDeviceControlList(abilityLists);
                    for (DeviceControlEntity deviceControlEntity : DeviceControlList){
                        JSONObject pushData = new JSONObject();
                        pushData.put(deviceControlEntity.getControlMode(),deviceControlEntity.getControlDefultValue());
                        messagePublish.publishTopic(GeneralPrefixEnum.DEVICE_TOPIC_PREFIX.getValue()+deviceControlEntity.getDeviceItemId(), JsonUtils.toJsonString(pushData));

                        StringBuffer actionLog = new StringBuffer();
                        actionLog.append("设备：");
                        actionLog.append(deviceControlEntity.getDeviceItemName());
                        actionLog.append("，设备id：");
                        actionLog.append(deviceControlEntity.getDeviceItemId());
                        actionLog.append("，功能：");
                        actionLog.append(deviceControlEntity.getControlName());
                        actionLog.append("，发送成功！");

                        //log.info("设备{},设备id{},功能:{},发送成功",deviceControlEntity.getDeviceItemName(),deviceControlEntity.getDeviceItemId(),deviceControlEntity.getControlName());
                        optLog(Long.parseLong(actionMapData.get("userId").toString()),actionMapData.get("userName").toString(),
                                "动作执行(设备控制):",
                                actionLog.toString(),
                                OperateTypeEnum.COMMAND.getValue());

                        allLog.append("触发动作执行(设备控制)：[ ");
                        allLog.append(actionLog);
                        allLog.append(" ]");
                    }
                }
                //数据转发
                if(actionMapData.containsKey("sendType")){
                    //设备预警消息推送
                    if(actionMapData.get("sendType").equals(1)){
                        messagePublish.publishTopic(actionMapData.get("pushTopic").toString(),actionMapData.get("pushData").toString());

                        //log.info("设备预警消息推送！预警数据:{},推送主题:{}",actionMapData.get("pushData").toString(),actionMapData.get("pushTopic").toString());

                        StringBuffer pushLog = new StringBuffer();
                        pushLog.append("，设备预警消息推送！预警数据：");
                        pushLog.append(actionMapData.get("pushData").toString());
                        pushLog.append("，推送主题:");
                        pushLog.append(actionMapData.get("pushTopic").toString());

                        optLog(Long.parseLong(actionMapData.get("userId").toString()),actionMapData.get("userName").toString(),
                                "设备预警消息推送",
                                pushLog.toString(),
                                OperateTypeEnum.TRIGGERALARM.getValue());

                        allLog.append("；触发数据流转-预警执行:");
                        allLog.append(pushLog);

                        //解除预警数据推送
                    }else if(actionMapData.get("sendType").equals(2)){
                        messagePublish.publishTopic(actionMapData.get("pushTopic").toString(),actionMapData.get("pushData").toString());
                        //log.info("设备解除预警数据推送！解除预警数据:{},推送主题:{}",actionMapData.get("pushData").toString(),actionMapData.get("pushTopic").toString());

                        StringBuffer pushLog = new StringBuffer();
                        pushLog.append("，设备预警消息推送！预警数据：");
                        pushLog.append(actionMapData.get("pushData").toString());
                        pushLog.append("，推送主题:");
                        pushLog.append(actionMapData.get("pushTopic").toString());

                        optLog(Long.parseLong(actionMapData.get("userId").toString()),actionMapData.get("userName").toString(),
                                "设备解除预警消息推送",
                                pushLog.toString(),
                                OperateTypeEnum.DISMISSALARM.getValue());

                        allLog.append("；触发数据流转-解除预警执行:");
                        allLog.append(pushLog);
                    }
                }
            }

            //数据转发
            if(actionMapData.containsKey("sendType")){
                //设备预警消息推送
                if(actionMapData.get("sendType").equals("1")){
                    messagePublish.publishTopic(actionMapData.get("pushTopic").toString(),actionMapData.get("pushData").toString());

                    StringBuffer pushLog = new StringBuffer();
                    pushLog.append("，设备预警消息推送！预警数据：");
                    pushLog.append(actionMapData.get("pushData").toString());
                    pushLog.append("，推送主题:");
                    pushLog.append(actionMapData.get("pushTopic").toString());

                    optLog(Long.parseLong(actionMapData.get("userId").toString()),actionMapData.get("userName").toString(),
                            "设备预警消息推送",
                            pushLog.toString(),
                            OperateTypeEnum.TRIGGERALARM.getValue());

                    allLog.append("；触发数据流转-预警执行：");
                    allLog.append(pushLog);
                //解除预警数据推送
                }else if(actionMapData.get("sendType").equals("2")){
                    messagePublish.publishTopic(actionMapData.get("pushTopic").toString(),actionMapData.get("pushData").toString());

                    //log.info("设备解除预警数据推送！解除预警数据:{},推送主题:{}",actionMapData.get("pushData").toString(),actionMapData.get("pushTopic").toString());

                    StringBuffer pushLog = new StringBuffer();
                    pushLog.append("，设备预警消息推送！预警数据：");
                    pushLog.append(actionMapData.get("pushData").toString());
                    pushLog.append("，推送主题:");
                    pushLog.append(actionMapData.get("pushTopic").toString());

                    optLog(Long.parseLong(actionMapData.get("userId").toString()),actionMapData.get("userName").toString(),
                            "设备解除预警消息推送",
                            pushLog.toString(),
                            OperateTypeEnum.DISMISSALARM.getValue());

                    allLog.append("；触发数据流转-解除预警执行:");
                    allLog.append(pushLog);
                    //log.info("情景模式规则信息：{}",pushLog);
                }
            }
            optLog(Long.parseLong(actionMapData.get("userId").toString()),actionMapData.get("userName").toString(),
                    "情景日志",
                    allLog.toString(),
                    OperateTypeEnum.SCENARIOMODE.getValue());
        }
    }



    public void optLog(Long userId,String userName,String logName,String message,int operateType)  {
        try {
            OptLogEntity logs = new OptLogEntity();
            InetAddress localHost = InetAddress.getLocalHost();
            logs.setIp(localHost.getHostAddress());
            logs.setUserAgent("情景模式-->规则命中-->动作触发");
            logs.setReqUri("情景模式");
            logs.setReqMethod("TCP");
            logs.setDuration(0);
            logs.setUserId(userId);
            logs.setRealName(userName);
            logs.setOperateType(operateType);
            logs.setModule("情景模式");
            //日志名称，是什么日志就叫什么
            logs.setName(logName);
            logs.setReqParams(message);
            String key = CacheKeyBuilder.logKey();
            redisCacheOps.leftPush(key, logs);
            //log.info("日志记录成功：{}",logs);
        }catch (Exception e){
            log.error("日志记录异常：{}",e.getMessage());
        }
    }
}