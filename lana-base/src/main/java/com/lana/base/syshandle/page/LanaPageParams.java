package com.lana.base.syshandle.page;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @auther liuyulet
 * @date 2024/3/21 09:47
 */
@Data
@NoArgsConstructor
@Tag(name = "分页查询公用参数")
public class LanaPageParams {

    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码")
    @Schema(description = "当前页码", required = true)
    Integer page;

    @NotNull(message = "每页条数不能为空")
    @Range(min = 1, max = 99999, message = "每页条数")
    @Schema(description = "每页条数", required = true)
    Integer pageSize;

    @Schema(description = "排序字段")
    String order;

    @Schema(description = "是否升序")
    boolean asc;



}
