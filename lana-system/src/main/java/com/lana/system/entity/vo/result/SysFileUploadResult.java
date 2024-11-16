package com.lana.system.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @auther liuyulet
 * @date 2024/3/30 17:31
 */
@Data
@Schema(description = "文件上传")
public class SysFileUploadResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "文件名称")
    private String fileName;

    @Schema(description = "文件地址")
    private String src;

    @Schema(description = "文件大小")
    private Long size;

    @Schema(description = "存储平台，描述字段结构")
    private String msg;
}
