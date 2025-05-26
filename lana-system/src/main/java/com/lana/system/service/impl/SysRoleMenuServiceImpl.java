package com.lana.system.service.impl;

import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.system.dao.SysRoleMenuDao;
import com.lana.system.entity.SysRoleMenuEntity;
import com.lana.system.service.SysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/29 11:52
 */
@Slf4j
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

    @Override
    public List<Long> getMenuIdList(Long id) {

        return baseMapper.getMenuIdList(id);
    }


    /**
     * 按角色 ID 列表删除
     * @param idList
     */
    @Override
    public void deleteByRoleIdList(List<Long> idList) {
        baseMapper.deleteByRoleIdList(idList);
    }


    @Override
    public void saveRoleMenu(Long roleid, List<Long> menuIdList) {

        baseMapper.saveRoleMenu(roleid, menuIdList);
    }


}
