package com.lana.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.common.utils.PageUtils;
import com.lana.modules.system.pojo.entity.SysUserDepartRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * (SysUserDepartRole)表服务接口
 *
 * @author liuyulet
 * @since 2022-10-04 18:22:01
 */
public interface SysUserDepartRoleService extends IService<SysUserDepartRoleEntity> {


    PageUtils queryPage(Map<String, Object> params);

    List<Map<String, Object>> getstepPage(List<Long> sss);
}
