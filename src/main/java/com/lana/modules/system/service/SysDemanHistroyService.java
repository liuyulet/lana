package com.lana.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.common.utils.PageUtils;
import com.lana.modules.system.pojo.entity.SysDeman;
import com.lana.modules.system.pojo.entity.SysDemanHistroyEntity;

import java.util.Map;

/**
 * 需求变更历史表(SysDemanHistroy)表服务接口
 *
 * @author liuyulet
 * @since 2022-10-06 13:55:13
 */
public interface SysDemanHistroyService  extends IService<SysDemanHistroyEntity> {


    PageUtils queryPage(Map<String, Object> params);
}
