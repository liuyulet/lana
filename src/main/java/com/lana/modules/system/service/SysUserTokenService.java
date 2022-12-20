
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
     * 将用户信息返回前端
     * @param userId  用户ID
     */
    Result createToken(long userId);

    /**
     * 退出用户
     * @param userId  用户ID
     */
    void logout(long userId);

}
