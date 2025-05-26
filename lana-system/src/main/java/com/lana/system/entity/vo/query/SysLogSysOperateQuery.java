package com.lana.system.entity.vo.query;

import com.lana.base.syshandle.page.LanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther liuyulet
 * @date 2024/3/30 17:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "系统操作日志查询")
public class SysLogSysOperateQuery extends LanaPageParams {

    @Schema(description = "日志类型")
    private Integer operateType;

    @Schema(description = "开始时间")
    private String startTime;

    @Schema(description = "结束时间")
    private String endTime;

}
