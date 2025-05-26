package com.lana.system.entity.vo.query;

import com.lana.base.syshandle.page.LanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther liuyulet
 * @date 2024/4/1 08:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "附件管理查询")
public class SysAttachmentQuery extends LanaPageParams {
    @Schema(description = "附件名称")
    private String name;

    @Schema(description = "存储平台")
    private String platform;
}
