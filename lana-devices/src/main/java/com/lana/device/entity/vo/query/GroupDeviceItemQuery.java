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
@Schema(description = "设备查询实例")
public class GroupDeviceItemQuery extends LanaPageParams {

    @Schema(description = "所属分组id")
    private Integer groupId;
}
