package com.lana.base.syshandle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/10/15 10:07
 */
@Getter
@AllArgsConstructor
public enum RuleValueEnum {


    /**
     * 设备控制节点
     */
    NODEDEVICECONTROL(1),
    /**
     * 数据流转节点
     */
    NODEDATAPORTERING(2),
    /**
     * 条件判断节点
     */
    NODECONDITIONAL(4),

    /**
     * 触发类型--监听设备
     */
    TRIGGERLISTENINGDEVICES(1),

    /**
     * 触发类型--定时任务
     */
    TRIGGERSCHEDULEDTASKS(2),

    /**
     * 控制设备类型--指定设备
     */
    CONTROLDEVICE(1),

    /**
     * 控制设备类型--指定分组
     */
    CONTROLGROUP(2),

    /**
     * 控制执行动作--控制已选设备开
     */
    CONTROLEXEDEVICECON(1),

    /**
     * 控制执行动作--控制已选设备关
     */
    CONTROLEXEDEVICECOFF(2),

    /**
     * 控制执行动作--自定义控制设备
     */
    CONTROLEXEDEVICECCUSTOMIZE(3),


    /** sequentially
     * 多设备时控制执行方式--按顺序依次执行
     */
    PERFORMSEQUENTIALLY(1),

    /**
     * 多设备时控制执行方式--不按顺序同步执行 无序执行
     */
    PERFORMUNSEQUENTIALLY(2),

    /**
     * 期限超时后执行--自动通过
     */
    AUTOPASSED(3),

    /**
     * 期限超时后执行--自动停止
     */
    AUTOSTOP(3),


    /**
     * 设备服务功能：1、控制功能
     */
    CONTROLFUNCTIONS(1),

    /**
     * 设备服务功能：2、采集功能
     */
    ACQUISITIONFUNCTION(2);



    private final Integer value;


}
