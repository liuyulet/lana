package com.lana.system.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/4/30 13:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "菜单删除ids")
public class SysMenusDeleteDTO {

    private List<Long> ids;

}
