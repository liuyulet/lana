

package com.lana.datasource.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @auther liuyulet
 * @date 2022/9/8 23:55
 */
@ConfigurationProperties(prefix = "dynamic")
public class DynamicDataSourceProperties {
    private Map<String, com.lana.datasource.properties.DataSourceProperties> datasource = new LinkedHashMap<>();

    public Map<String, com.lana.datasource.properties.DataSourceProperties> getDatasource() {
        return datasource;
    }

    public void setDatasource(Map<String, com.lana.datasource.properties.DataSourceProperties> datasource) {
        this.datasource = datasource;
    }
}
