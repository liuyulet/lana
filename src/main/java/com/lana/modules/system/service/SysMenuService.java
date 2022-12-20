package com.lana.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.modules.system.pojo.dto.MenuForRoleDTO;
import com.lana.modules.system.pojo.entity.SysMenuEntity;

import java.util.HashMap;
import java.util.List;


/**
 * 菜单管理(SysMenu)表服务接口
 * @author liuyulet
 * @since 2022-10-05 15:32:26
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    List<SysMenuEntity> getNavData(String userid);

    List<HashMap<String,Object>> getMenurole(Long role);

    void userForDepart(MenuForRoleDTO menuForRoleDTO);
}

