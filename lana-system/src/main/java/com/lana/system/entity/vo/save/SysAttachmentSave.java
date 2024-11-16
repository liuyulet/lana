package com.lana.system.entity.vo.save;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther liuyulet
 * @date 2024/4/1 09:01
 */
@Data
@Schema(description = "附件管理")
public class SysAttachmentSave implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "附件名称")
    private String name;

    @Schema(description = "附件地址")
    private String url;

    @Schema(description = "附件大小")
    private Long size;

    @Schema(description = "存储平台")
    private String platform;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;
}
