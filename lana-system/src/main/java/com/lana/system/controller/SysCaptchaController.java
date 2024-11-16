package com.lana.system.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.system.entity.vo.result.SysCaptchaResult;
import com.lana.system.service.SysCaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther liuyulet
 * @date 2024/3/21 16:03
 */
@Slf4j
@RestController
@RequestMapping("/sys/captcha")
@ApiSupport(author = "liuyulet")
@Tag(name = "验证码")
public class SysCaptchaController {


    @Resource
    private SysCaptchaService sysCaptchaService;

    /**
     * 获取验证码
     * 本接口无需参数，调用后返回一个验证码结果对象。
     * @return Result<SysCaptchaResult> 返回验证码结果，其中包含验证码图片的Base64编码。
     */
    @GetMapping("/getCaptcha")
    public LanaResult<SysCaptchaResult> captcha() {

        // 生成并返回验证码
        return LanaResult.ok(sysCaptchaService.generate());
    }


    /**
     * 检查验证码功能是否开启。
     * 在配置文件中设置captchaEnabled属性即可
     *
     * @return {@link LanaResult <Boolean>} 结果对象，其中的 Boolean 值表示验证码功能是否开启。
     */
    @GetMapping("/enabled")
    @Operation(summary = "是否开启验证码")
    public LanaResult<Boolean> captchaEnabled() {
        // 调用服务层方法，获取验证码功能是否开启的状态
        return sysCaptchaService.captchaEnabled();
    }


}
