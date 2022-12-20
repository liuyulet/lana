
package com.lana.modules.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lana.modules.system.dao.SysDepartDao;
import com.lana.modules.system.dao.SysRoleDao;
import com.lana.modules.system.pojo.dto.UserForDepartDTO;
import com.lana.modules.system.pojo.dto.UserForRoleDTO;
import com.lana.modules.system.pojo.entity.SysRoleEntity;
import com.lana.modules.system.service.SysRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色
 *
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public void userForRole(UserForRoleDTO userForRoleDTO) {
        //stream循环插入数据
        if(userForRoleDTO.getUserId().length>0){
            int[] users= userForRoleDTO.getUserId();
            List<Integer> userList= Arrays.stream(users).boxed().collect(Collectors.toList());
            sysRoleDao.userForRole(userForRoleDTO.getRoleId(),userList);
        }
        if (userForRoleDTO.getUserUpdateId().length>0) {
            int[] updatUsers= userForRoleDTO.getUserUpdateId();
            List<Integer> userUpdateList= Arrays.stream(updatUsers).boxed().collect(Collectors.toList());
            sysRoleDao.userUpdatForRole(userForRoleDTO.getRoleId(),userUpdateList);
        }
    }


}
