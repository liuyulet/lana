package com.lana.device.entity.vo.query;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lana.base.syshandle.tree.HandleTree;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@Schema(description = "产品类型")
public class DeviceProductTypeQuery{

    /**
     * 机构名称
     */
    @Schema(description = "产品名称")
    private String label;

}
