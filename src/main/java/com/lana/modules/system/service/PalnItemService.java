package com.lana.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.common.utils.PageUtils;
import com.lana.modules.system.pojo.entity.PalnItemEntity;
import com.lana.modules.system.pojo.entity.SysUserDepartRoleEntity;

import java.util.Map;

/**
 * (PalnItem)表服务接口
 *
 * @author liuyulet
 * @since 2022-11-03 15:16:13
 */
public interface PalnItemService  extends IService<PalnItemEntity> {


    PageUtils queryPage(Map<String, Object> params);
}
