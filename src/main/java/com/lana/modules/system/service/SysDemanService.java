package com.lana.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.common.utils.PageUtils;
import com.lana.modules.system.pojo.dto.NextForDemdDTO;
import com.lana.modules.system.pojo.dto.UserForDemdDTO;
import com.lana.modules.system.pojo.entity.SysDeman;
import com.lana.modules.system.pojo.entity.SysUserEntity;


import java.util.Map;

/**
 * (SysDeman)表服务接口
 *
 * @author liuyulet
 * @since 2022-09-30 11:25:42
 */
public interface SysDemanService extends IService<SysDeman> {


    PageUtils queryPage(Map<String, String> params, SysUserEntity userEntit);

    void userForDeman(UserForDemdDTO userForDemdDTO);

    PageUtils getMyDeman(Map<String, Object> params);

    void nextForDeman(NextForDemdDTO nextForDemdDTO);
}
