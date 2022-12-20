package com.lana.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.common.utils.PageUtils;
import com.lana.modules.system.pojo.entity.SysProjectEntity;


import java.util.Map;

/**
 * (SysProject)表服务接口
 *
 * @author liuyulet
 * @since 2022-10-06 11:23:37
 */
public interface SysProjectService extends IService<SysProjectEntity> {

    PageUtils queryPage(Map<String, Object> params);

    SysProjectEntity getProjects(String demanProject);
}
