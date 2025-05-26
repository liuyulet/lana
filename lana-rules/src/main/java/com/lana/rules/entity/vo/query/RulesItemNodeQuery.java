package com.lana.rules.entity.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@Schema(description = "规则节点实体")
public class RulesItemNodeQuery {

    /**
     * 规则实例id
     */
    @NotNull(message = "规则实例id")
    @Schema(description = "rulesId")
    private Long rulesId;

}
