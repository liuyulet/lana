/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.lana.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.modules.system.pojo.entity.SysCaptchaEntity;

import java.awt.image.BufferedImage;

/**
 * 验证码
 * @auther liuyulet
 * @date 2022/9/27 12:54
 */
public interface SysCaptchaService extends IService<SysCaptchaEntity> {

    /**
     * 获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     * @param uuid  uuid
     * @param code  验证码
     * @return true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}
