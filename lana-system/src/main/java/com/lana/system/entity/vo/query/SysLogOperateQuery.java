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
@Schema(description = "操作日志查询")
public class SysLogOperateQuery extends LanaPageParams {
    @Schema(description = "用户")
    private Long userId;



}
