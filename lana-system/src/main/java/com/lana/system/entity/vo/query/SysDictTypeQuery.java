package com.lana.system.entity.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther liuyulet
 * @date 2024/3/31 17:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "字典查询实体")
public class SysDictTypeQuery{
    @Schema(description = "字典编码", required = true)
    private String code;

    @Schema(description = "字典名称", required = true)
    private String name;
}
