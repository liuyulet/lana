
package com.lana.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.lana.modules.system.pojo.entity.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 * @auther liuyulet
 * @date 2022/9/27 12:54
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity> {

}
