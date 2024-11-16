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
@Schema(description = "设备分组")
public class DeviceGroupQuery extends LanaPageParams {

    /**
     * 机构名称
     */
    @Schema(description = "分组名称")
    private String name;

}
