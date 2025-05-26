package com.lana.base.operatelog.annotations;


import com.lana.base.operatelog.enums.OperateTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志
 * @auther liuyulet
 * @date 2024/3/16 14:22
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OptLog {
    /**
     * 模块名
     */
    String module() default "";

    /**
     * 操作名
     */
    String name() default "";

    /**
     * 是否保存请求的参数
     */


    /**
     * 操作类型
     */
    OperateTypeEnum[] type() default OperateTypeEnum.QUERY;
}
