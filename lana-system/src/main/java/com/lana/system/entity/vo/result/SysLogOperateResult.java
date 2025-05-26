package com.lana.system.entity.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @auther liuyulet
 * @date 2024/3/30 17:08
 */
@Data
@Schema(description = "个人操作日志")
public class SysLogOperateResult implements Serializable {
    private static final long serialVersionUID = 1L;


    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "操作人")
    private String realName;

    @Schema(description = "模块名")
    private String module;

    @Schema(description = "操作名")
    private String name;

    @Schema(description = "请求URI")
    private String reqUri;

    @Schema(description = "请求方法")
    private String reqMethod;

    @Schema(description = "请求参数")
    private String reqParams;

    @Schema(description = "操作IP")
    private String ip;

    @Schema(description = "登录地点")
    private String address;

    @Schema(description = "User Agent")
    private String userAgent;

    @Schema(description = "操作类型")
    private Integer operateType;

    @Schema(description = "执行时长")
    private Integer duration;

    @Schema(description = "操作状态")
    private Integer status;

    @Schema(description = "返回消息")
    private String resultMsg;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date ts;

}
