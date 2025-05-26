package com.lana.rules.entity.vo.query;

import com.lana.base.syshandle.page.LanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@Schema(description = "规则分页查询实体")
public class RulesItemPageQuery extends LanaPageParams {

    /**
     * 规则名称
     */
    @Schema(description = "规则名称")
    private String name;

}
