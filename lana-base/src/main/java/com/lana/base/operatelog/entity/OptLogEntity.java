package com.lana.base.operatelog.entity;

import lombok.Data;

/**
 * 操作日志
 * @auther liuyulet
 * @date 2024/3/16 14:26
 */
@Data
public class OptLogEntity {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 操作人
     */
    private String realName;

    /**
     * 模块名
     */
    private String module;

    /**
     * 操作名
     */
    private String name;

    /**
     * 请求URI
     */
    private String reqUri;

    /**
     * 请求方法
     */
    private String reqMethod;

    /**
     * 请求参数
     */
    private String reqParams;

    /**
     * 操作IP
     */
    private String ip;


    /**
     * User Agent
     */
    private String userAgent;

    /**
     * 操作类型
     */
    private Integer operateType;

    /**
     * 执行时长
     */
    private Integer duration;

    /**
     * 操作状态
     */
    private Integer status;


}
