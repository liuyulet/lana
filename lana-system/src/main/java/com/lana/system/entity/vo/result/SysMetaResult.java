package com.lana.system.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @auther liuyulet
 * @date 2024/3/29 17:55
 */
@Data
@Schema(description = "菜单s")
public class SysMetaResult  implements Serializable {
    private static final long serialVersionUID = 1L;
    //private Long menu_id;
    private String title;
    private Boolean hidden;
    private Boolean affix;
    private String icon;
    private String type;
    private Boolean hiddenBreadcrumb;
    private Boolean active;
    private String color;
    private Boolean fullpage;
    private String role;
    private Integer sort;
}
