package com.lana.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lana.modules.system.dao.SysMenuDao;
import com.lana.modules.system.pojo.dto.MenuForRoleDTO;
import com.lana.modules.system.pojo.entity.SysMenuEntity;
import com.lana.modules.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单管理(SysMenu)表服务实现类
 * @author liuyulet
 * @since 2022-10-05 15:32:26
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    public List<SysMenuEntity> getNavData(String userid) {
        return sysMenuDao.getNavData(userid);
    }

    @Override
    public List<HashMap<String,Object>> getMenurole(Long role) {
        return sysMenuDao.getMenurole(role);
    }

    @Override
    public void userForDepart(MenuForRoleDTO menuForRoleDTO) {
        //删除该角色的其他所有菜单的授权
        sysMenuDao.deleteRolMen(menuForRoleDTO.getRoleId());
        //新增新的授权信息
        if (menuForRoleDTO.getMenuUpdateId().length>0) {
            int[] updatUsers= menuForRoleDTO.getMenuUpdateId();
            List<Integer> userUpdateList= Arrays.stream(updatUsers).boxed().collect(Collectors.toList());
            sysMenuDao.userUpdateForDepart(menuForRoleDTO.getRoleId(),userUpdateList);
        }
    }
}

