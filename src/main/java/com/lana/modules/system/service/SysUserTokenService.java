/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.lana.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.common.utils.Result;
import com.lana.modules.system.pojo.entity.SysUserTokenEntity;


/**
 * 用户Token
 * @auther liuyulet
 * @date 2022/9/27 12:54
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

    /**
     * 生成token
     * @param userId  用户ID
     */
    Result createToken(long userId);

    /**
     * 退出，修改token值
     * @param userId  用户ID
     */
    void logout(long userId);

}
