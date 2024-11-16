package com.lana.base.mybatis.handler;

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
public class FillMetaObjectHandler implements MetaObjectHandler {
    private final static String CREATE_TIME = "createTime";
    private final static String CREATOR = "creator";
    private final static String UPDATE_TIME = "updateTime";
    private final static String UPDATER = "updater";
    private final static String DELETED = "deleted";
    private final static String CREATOR_NAME= "creatorName";
    private final static String UPDATER_NAME = "updaterName";

    @Override
    public void insertFill(MetaObject metaObject) {
        UserDetail user = SecurityUser.getUser();
        Date date = new Date();

        // 用户字段填充
        if (user != null) {
            // 创建者
            setFieldValByName(CREATOR, user.getId(), metaObject);
            // 更新者
            setFieldValByName(UPDATER, user.getId(), metaObject);
            // 创建者账号名称
            setFieldValByName(CREATOR_NAME,"["+user.getRealName()+":"+user.getUsername()+"]",metaObject);
            // 更新者账号名称
            setFieldValByName(UPDATER_NAME,"["+user.getRealName()+":"+user.getUsername()+"]",metaObject);
        }
        // 创建时间
        setFieldValByName(CREATE_TIME, date, metaObject);
        // 更新时间
        setFieldValByName(UPDATE_TIME, date, metaObject);
        // 删除标识
        setFieldValByName(DELETED, 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新者
        setFieldValByName(UPDATER, SecurityUser.getUserId(), metaObject);
        // 更新时间
        setFieldValByName(UPDATE_TIME, new Date(), metaObject);
        // 更新者账号名称
        setFieldValByName(UPDATER_NAME,"["+SecurityUser.getUser().getRealName()+":"+SecurityUser.getUser().getUsername()+"]",metaObject);
    }
}
