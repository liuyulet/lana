package com.lana.device.entity.vo.query;

import com.lana.base.syshandle.page.LanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/11/15 10:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "边缘实例查询实体")
public class DeviceEdgesQuery extends LanaPageParams {
    /**
     * 机构名称
     */
    @Schema(description = "实例名称")
    private String name;
}
