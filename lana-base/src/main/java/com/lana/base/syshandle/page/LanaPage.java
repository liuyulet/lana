package com.lana.base.syshandle.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/4/14 19:48
 */
@Data
@Schema(description = "设置分页格式")
public class LanaPage<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "总数据数")
    private long total;

    @Schema(description = "页面数")
    private long page;

    @Schema(description = "页面大小")
    private long pageSize;

    @Schema(description = "列表数据")
    private List<T> rows;

    /**
     * 分页
     * @param rows 列表数据
     * @param total 总数据数
     * @param page 页面数
     * @param pageSize 页面大小
     */
    public LanaPage(List<T> rows, long total, long page, long pageSize) {
        this.rows = rows;
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
    }
}
