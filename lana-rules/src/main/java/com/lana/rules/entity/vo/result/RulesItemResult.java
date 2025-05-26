package com.lana.rules.entity.vo.result;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:31
 */
@Data
@Schema(description = "规则信息")
public class RulesItemResult {

    /**
     * 规则脚本名称
     */
    @Schema(description = "id")
    private Long id;
    /**
     * 规则脚本名称
     */
    @Schema(description = "规则脚本名称")
    private String asName;
    /**
     * 1:脚本，2:表达式
     */
    @Schema(description = "1:脚本，2:表达式")
    private Integer asType;

    /**
     * 表达式内容
     */
    @Schema(description = "表达式内容")
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


    /**
     * 规则类型：4条件类型，1设备控制类型，2数据流转类型
     */
    @Schema(description = "规则类型")
    private Integer ruleType;




    /**
     * 创建者
     */
    @Schema(description = "创建者")
    private Long  creator;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    /**
     * 更新者
     */
    @Schema(description = "更新者")
    private Long  updater;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;


    /**
     * 删除标记
     */
    @Schema(description = "删除标记")
    private Integer deleted;

    /**
     * 创建者
     */
    @Schema(description = "创建者")
    private String  creatorName;

    /**
     * 更新者
     */
    @Schema(description = "更新者")
    private String  updaterName;
}
