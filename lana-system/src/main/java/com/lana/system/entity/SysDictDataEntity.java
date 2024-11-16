package com.lana.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lana.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther liuyulet
 * @date 2024/3/31 21:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict_data")
public class SysDictDataEntity extends BaseEntity {
    /**
     * 字典类型ID
     */
    private Long dic;
    /**
     * 字典标签
     */
    @TableField("`name`")
    private String name;
    /**
     * 字典值
     */
    @TableField("`key`")
    private String key;
    /**
     * 备注
     */
    private String yx;
    /**
     * 排序
     */
    private Integer sort;

}
