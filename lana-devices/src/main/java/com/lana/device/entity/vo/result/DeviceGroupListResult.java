package com.lana.device.entity.vo.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@Schema(description = "设备分组规则列表查询")
public class DeviceGroupListResult {

    @Schema(description = "id")
    private Long id;

    /**
     * 设备分组
     */
    @Schema(description = "设备分组名称")
    private String label;

}
