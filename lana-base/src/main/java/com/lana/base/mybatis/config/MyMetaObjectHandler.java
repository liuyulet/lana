package com.lana.base.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lana.base.security.token.user.SecurityUser;
import com.lana.base.security.token.user.UserDetail;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 自动填充
 * 自动填充字段
 * @auther liuyulet
 * @date 2024/3/16 14:18
 */
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        UserDetail user = SecurityUser.getUser();
        Date date = new Date();
        // 创建者
        strictInsertFill(metaObject,FieldConstants.CREATOR.getFieldName(),Long.class,user.getId());
        // 创建者账号名称
        strictInsertFill(metaObject,FieldConstants.CREATOR_NAME.getFieldName(),String.class,"["+user.getRealName()+":"+user.getUsername()+"]");
        // 创建时间
        strictInsertFill(metaObject,FieldConstants.CREATE_TIME.getFieldName(),Date.class,date);
        // 删除标识
        strictInsertFill(metaObject,FieldConstants.DELETED.getFieldName(),Integer.class,0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        UserDetail user = SecurityUser.getUser();
        Date date = new Date();
        // 更新者
        strictUpdateFill(metaObject,FieldConstants.UPDATER.getFieldName(),Long.class,user.getId());
        // 更新者账号名称
        strictUpdateFill(metaObject,FieldConstants.UPDATER_NAME.getFieldName(),String.class,"["+user.getRealName()+":"+user.getUsername()+"]");
        // 更新时间
        strictUpdateFill(metaObject,FieldConstants.UPDATE_TIME.getFieldName(),Date.class,date);

    }
}
