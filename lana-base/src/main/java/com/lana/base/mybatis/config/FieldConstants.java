package com.lana.base.mybatis.config;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/11/25 15:11
 */
public enum FieldConstants {
    CREATE_TIME("createTime"),
    CREATOR("creator"),
    UPDATE_TIME("updateTime"),
    UPDATER("updater"),
    DELETED("deleted"),
    CREATOR_NAME("creatorName"),
    UPDATER_NAME("updaterName");

    private final String fieldName;

    FieldConstants(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
