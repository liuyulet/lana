package com.lana.base.operatelog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**操作类型
 * @auther liuyulet
 * @date 2024/3/16 14:26
 */
@Getter
@AllArgsConstructor
public enum OperateTypeEnum {

    /**
     * 设备操作日志
     */
    COMMAND(0),

    /**
     * 查询
     */
    QUERY(1),
    /**
     * 新增
     */
    INSERT(2),
    /**
     * 修改
     */
    UPDATE(3),
    /**
     * 删除
     */
    DELETE(4),

    /**
     * 设备告警日志
     */
    TRIGGERALARM(5),
    /**
     * 解除告警日志
     */
    DISMISSALARM(6),
    /**
     * 平台推送日志
     */
    PLATFORMPUSH(7),
    /**
     * 接入协议日志
     */
    ACCESSPROTOCOL(8),

    /**
     * 情景模式日志
     */
    SCENARIOMODE(9),

    /**
     * 预警消息记录
     */
    WARNINGMESSAGES(10),


    //父级列表
    /**
     * 系统日志
     */
    SYSTEM(11),
    /**
     * 设备日志
     */
    DEVICE(12),
    /**
     * 通讯日志
     */
    COMMUNICATION(13);


    private final int value;
}
