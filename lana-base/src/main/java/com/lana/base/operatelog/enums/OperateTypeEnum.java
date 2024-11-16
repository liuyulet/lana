package com.lana.base.operatelog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**操作类型
 * @auther liuyulet
 * @date 2024/3/16 14:26
 */
@Getter
@AllArgsConstructor
public enum OperateTypeEnum {
    /**
     * 查询
     */
    QUERY(1),
    /**
     * 新增
     */
    INSERT(2),
    /**
     * 修改
     */
    UPDATE(3),
    /**
     * 删除
     */
    DELETE(4);


    private final int value;
}
