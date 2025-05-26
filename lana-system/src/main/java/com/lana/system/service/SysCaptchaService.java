package com.lana.system.service;

import com.lana.base.syshandle.result.LanaResult;
import com.lana.system.entity.vo.result.SysCaptchaResult;

/**
 * @auther liuyulet
 * @date 2024/3/21 16:04
 */
public interface SysCaptchaService {

    /**
     * 生成验证码
     */
    SysCaptchaResult generate();

    /**
     * 验证码效验
     *
     * @param key  key
     * @param code 验证码
     * @return true：成功  false：失败
     */
    boolean validate(String key, String code);


    /**
     * 是否开启验证码
     * @return
     */
    LanaResult<Boolean> captchaEnabled();
}
