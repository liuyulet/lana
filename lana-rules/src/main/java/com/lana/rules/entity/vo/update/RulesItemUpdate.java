package com.lana.rules.entity.vo.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:31
 */
@Data
@Schema(description = "规则修改实体")
public class RulesItemUpdate {

    /**
     * id
     */
    @Schema(description = "id")
    private Long id;
    /**
     * 规则脚本名称
     */
    @Schema(description = "规则脚本名称")
    @NotNull(message = "规则脚本名称不能为空")
    private String asName;
    /**
     * 1:脚本，2:表达式
     */
    @Schema(description = "1:脚本，2:表达式")
    @NotNull(message = "规则类型不能为空")
    private Integer asType;

    /**
     * 表达式内容
     */
    @Schema(description = "表达式内容")
    @NotNull(message = "规则内容不能为空")
    private String asContent;

    /**
     * 文件路径
     */
    @Schema(description = "文件路径")
    private String asPath;

    /**
     * 规则说明
     */
    @Schema(description = "规则说明")
    private String asRemark;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Integer asEnable;


    @Schema(description = "规则类型")
    private Integer ruleType;

    /**
     * 设备id
     */
/*    @Schema(description = "设备id")
    private Long deviceItemId;*/
}
