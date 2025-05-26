package com.lana.base.utils;

import cn.hutool.core.util.StrUtil;
import com.lana.base.syshandle.exception.LanaException;

/**
 * @auther liuyulet
 * @date 2024/3/16 13:59
 *
 */
public class EmptyUtils {

    /**
     *
     * @param str
     * @param variable
     */
    public static void isBlank(String str, String variable) {
        if (StrUtil.isBlank(str)) {
            throw new LanaException(variable + "不能为空");
        }
    }

    public static void isNull(Object object, String variable) {
        if (object == null) {
            throw new LanaException(variable + "不能为空");
        }
    }

}
