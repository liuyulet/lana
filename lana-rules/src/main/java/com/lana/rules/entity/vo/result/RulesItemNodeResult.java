package com.lana.rules.entity.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:31
 */
@Data
@Schema(description = "规则节点")
public class RulesItemNodeResult {

    /**
     * 所属规则实例id
     */
    @Schema(description = "所属规则实例id")
    private Long rulesId;

    /**
     * 实例名称
     */
    @Schema(description = "实例名称")
    private String name;

    /**
     * 节点设置
     */
    @Schema(description = "节点设置")
    private String nodeConfig;
}
