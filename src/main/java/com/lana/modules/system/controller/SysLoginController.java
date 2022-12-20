

package com.lana.modules.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.common.utils.Result;

import com.lana.modules.system.pojo.dto.SysLoginDTO;
import com.lana.modules.system.pojo.entity.SysUserEntity;
import com.lana.modules.system.service.SysCaptchaService;
import com.lana.modules.system.service.SysUserService;
import com.lana.modules.system.service.SysUserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录接口
 * @auther liuyulet
 * @date 2022/9/27 12:54
 */
@Api(tags = "登录接口")
@ApiSupport(author = "liuyulet")
@RestController
public class SysLoginController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private SysCaptchaService sysCaptchaService;

    /**
     * 验证码
     */
    @ApiOperation(value = "验证码", notes = "验证码")
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录
     */
    @ApiOperation(value = "登录接口", notes = "登录接口")
    @PostMapping("/sys/login")
    public Result login(@RequestBody SysLoginDTO form)  {
		/*boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
		if(!captcha){
			return Result.error("验证码不正确");
		}*/
        SysUserEntity user = sysUserService.queryByUserName(form.getUsername());
        if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return Result.error("账号或密码不正确");
        }
        if (user.getStatus() == 0) {
            return Result.error("账号已被锁定,请联系管理员");
        }
        Result result = sysUserTokenService.createToken(user.getUserId());
        JSONObject res= (JSONObject) result.getResult();
        res.put("userAccount",user.getUsername());
        res.put("userId",user.getUserId());
        res.put("userFullname",user.getFullname());
        return result;
    }


    /**
     * 退出
     */
    @ApiOperation(value = "退出", notes = "退出")
    @PostMapping("/sys/logout")
    public Result logout() {
        sysUserTokenService.logout(getUserId());
        return Result.ok();
    }

}
