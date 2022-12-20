
package com.lana.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.modules.system.pojo.dto.UserForDepartDTO;
import com.lana.modules.system.pojo.dto.UserForRoleDTO;
import com.lana.modules.system.pojo.entity.SysRoleEntity;


import java.util.List;
import java.util.Map;


/**
 * 角色
 *
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    void userForRole(UserForRoleDTO userForRoleDTO);
}
