package com.lana.device.entity.vo.query;

import com.lana.base.syshandle.page.LanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "设备查询实体")
public class DeviceItemQuery extends LanaPageParams {
    /**
     * 机构名称
     */
    @Schema(description = "设备名称")
    private String name;

    @Schema(description = "所属部门，即product_type_id")
    private Integer groupId;
}
