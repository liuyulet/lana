package com.lana.system.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.RedisCacheOps;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.system.entity.vo.result.SysCaptchaResult;
import com.lana.system.service.SysCaptchaService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @auther liuyulet
 * @date 2024/3/21 16:08
 */
@Slf4j
@Service
public class SysCaptchaServiceImpl implements SysCaptchaService {

    @Resource
    private RedisCacheOps redisCacheOps;
    @Value("${spring.captchaEnabled}")
    private Boolean captchaEnabled;


    @Override
    public SysCaptchaResult generate() {
        // 如果开启了则captchaEnabled字段为ture，否则为false，尽量一个请求解决
        SysCaptchaResult captchaVO = new SysCaptchaResult();
        if (!captchaEnabled) {
            captchaVO.setCaptchaEnabled(false);
            return captchaVO;
        }

        // 生成验证码key
        String key = UUID.randomUUID().toString() +  Instant.now().getNano();

        // 生成验证码
        SpecCaptcha captcha = new SpecCaptcha(150, 40);
        captcha.setLen(5);
        captcha.setCharType(Captcha.FONT_9);
        String image = captcha.toBase64();

        // 保存到缓存
        String redisKey = CacheKeyBuilder.captchaKey(key);
        redisCacheOps.set(redisKey, captcha.text(), 300);
        captchaVO.setKey(key);
        captchaVO.setImage(image);
        captchaVO.setCaptchaEnabled(true);
        return captchaVO;
    }

    @Override
    public boolean validate(String key, String code) {

        if (StrUtil.isBlank(key) || StrUtil.isBlank(code)) {
            return false;
        }
        key = CacheKeyBuilder.captchaKey(key);
        String captcha = (String) redisCacheOps.get(key);
        // 删除验证码
        if (captcha != null) {
            redisCacheOps.delete(key);
        }
        // 效验成功
        return code.equalsIgnoreCase(captcha);
    }

    @Override
    public LanaResult<Boolean> captchaEnabled() {
        return LanaResult.ok(captchaEnabled);
    }


}
