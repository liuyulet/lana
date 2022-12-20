

package com.lana.common.validator;

import com.lana.common.exception.LException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 * lyl
 * @author
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new LException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new LException(message);
        }
    }
}
