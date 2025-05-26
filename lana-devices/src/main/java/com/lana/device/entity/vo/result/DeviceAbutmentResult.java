package com.lana.device.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/10/17 21:22
 */
@Data
@Schema(description = "查询设备mqtt协议返回结果")
public class DeviceAbutmentResult {

    @Schema(description = "现绑定的协议ID")
    private Long id;

    @Schema(description = "产品连接方式")
    private Integer deviceType;

    @Schema(description = "所属边缘计算id")
    private Long edgeId;

    @Schema(description = "协议信息")
    private List<ProtocolResult> rotocolResult;


    @Schema(description = "边缘实例实体")
    private DeviceEdgesItemResult deviceEdgesItemResult;
/*    @Schema(description = "协议实体")
    private List<ProtocolResult> rotocolResult;*/
    @Schema(description = "边缘设备信息")
    private List<EdgesItemDataResult> edgesItemData;


}
