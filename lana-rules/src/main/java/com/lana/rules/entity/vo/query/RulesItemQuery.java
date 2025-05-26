package com.lana.rules.entity.vo.query;

import com.lana.base.syshandle.page.LanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@Schema(description = "规则查询实体")
public class RulesItemQuery{

    /**
     * 设备id
     */
    @NotNull(message = "设备id不能为空")
    @Schema(description = "设备id")
    private Long deviceId;

}
