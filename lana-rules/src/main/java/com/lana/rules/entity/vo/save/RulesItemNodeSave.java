package com.lana.rules.entity.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:31
 */
@Data
@Schema(description = "规则修改实体")
public class RulesItemNodeSave {


    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

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
