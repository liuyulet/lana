/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.lana.modules.system.pojo.dto;

import lombok.Data;

/**
 * 登录表单
 *
 */
@Data
public class SysLoginDTO {
    private String username;
    private String password;
    private String captcha;
    private String uuid;


}
