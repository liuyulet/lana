package com.lana.system.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/31 17:47
 */
@Data
@Schema(description = "全部字典")
public class SysDictResult {

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "字典数据列表")
    private List<DictData> dataList = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @Schema(description = "字典数据")
    public static class DictData {
        @Schema(description = "字典标签")
        private String dictLabel;

        @Schema(description = "字典值")
        private String dictValue;

        @Schema(description = "标签样式")
        private String labelClass;
    }

}
