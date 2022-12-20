

package com.lana.common.utils;

import java.util.HashMap;


/**
 * @auther liuyulet
 * @date 2022/9/8 23:37
 */
public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
