package com.lana.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.lana.base.syshandle.exception.LanaException;
import com.lana.base.utils.TreeUtils;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.base.security.token.user.UserDetail;
import com.lana.system.dao.SysMenusDao;
import com.lana.system.entity.*;
import com.lana.system.entity.vo.result.SysMenusNavResult;
import com.lana.system.entity.vo.result.SysMenusResult;
import com.lana.system.entity.vo.save.SysMenusSave;
import com.lana.system.entity.vo.update.SysMenusUpdate;
import com.lana.base.syshandle.enums.SuperAdminEnum;
import com.lana.system.service.SysMenusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @auther liuyulet
 * @date 2024/3/19 13:48
 */
@Slf4j
@Service
public class SysMenusServiceImpl extends BaseServiceImpl<SysMenusDao, SysMenusEntity> implements SysMenusService {



    /**
     * nav菜单导航
     * @param user
     * @return
     */
    @Override
    public SysMenusNavResult getNavMenuList(UserDetail user) {

        List<SysMenusResult> menuList = null;
        SysMenusNavResult sysNavResult = new SysMenusNavResult();
        // 系统管理员，拥有最高权限
        if (user.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
            menuList = baseMapper.getMenus();
        } else {
            //非管理员
            long userid = user.getId();
            menuList = baseMapper.getUserMenuList(userid);
        }
        //Tree处理
        sysNavResult.setMenu(TreeUtils.build(menuList));
        sysNavResult.setPermissions(user.getAuthoritySet());
        //手动添加仪表盘
        List<String> dashboardGrids = new ArrayList<>();
        dashboardGrids.add("welcome");
        dashboardGrids.add("ver");
        dashboardGrids.add("time");
        dashboardGrids.add("progress");
        dashboardGrids.add("echarts");
        dashboardGrids.add("about");

        sysNavResult.setDashboardGrid(dashboardGrids);
        return sysNavResult;
    }

    @Override
    public List<SysMenusResult> getMenuList(UserDetail user) {
        List<SysMenusResult> menuList = null;
        // 系统管理员，拥有最高权限
        if (user.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
            menuList = baseMapper.getMenus();
        }else {
            menuList = baseMapper.getUserMenuList(user.getId());
        }
        return TreeUtils.build(menuList);
    }

    /**
     * 新增菜单
     * @param saveVO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysMenusEntity saveMenu(SysMenusSave saveVO) {
        try {
            //增加菜单
            SysMenusEntity sysMenusEntity = new SysMenusEntity();
            BeanUtils.copyProperties(saveVO,sysMenusEntity);
            BeanUtils.copyProperties(saveVO.getMeta(),sysMenusEntity);
            // todo
            //拼接字段
            baseMapper.insert(sysMenusEntity);
            return sysMenusEntity;
        }catch (Exception e){
            log.info("新增菜单失败，请检查",e);
            throw  new LanaException("新增菜单失败，请检查");
        }
    }

    @Override
    public void updateByData(SysMenusUpdate updateVO) {
        try {
            //删除所有的api
            //增加、修改菜单
            SysMenusEntity sysMenusEntity = new SysMenusEntity();
            BeanUtils.copyProperties(updateVO,sysMenusEntity);
            sysMenusEntity.setCode(updateVO.getApiList().size() > 0 ? updateVO.getApiList().get(0).getCode() : null);
            sysMenusEntity.setUrl(updateVO.getApiList().size()> 0 ? updateVO.getApiList().get(0).getUrl() : null);
            BeanUtils.copyProperties(updateVO.getMeta(),sysMenusEntity);
            baseMapper.updateById(sysMenusEntity);
            // todo
        }catch (Exception e){
            log.info("修改菜单失败，请检查",e);
            throw  new LanaException("修改菜单失败，请检查");
        }
    }

    @Override
    public void delete(List<Long> ids) {
            baseMapper.deleteBatchIds(ids);
    }

    @Override
    public Set<String> getUserAuthority(UserDetail userDetail) {
        List<String> authorityList;
        if (userDetail.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
            authorityList = baseMapper.getAuthorityList();
        } else {
            authorityList = baseMapper.getUserAuthorityList(userDetail.getId());
        }

        // 用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String authority : authorityList) {
            if (StrUtil.isBlank(authority)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(authority.trim().split(",")));
        }

        return permsSet;
    }

    /**
     * 获取指定角色的菜单列表以及。
     *
     * @param roleId 角色的唯一标识符。
     * @return 返回一个包含角色菜单结果的列表。当前实现返回null，子类应覆写此方法以提供具体实现。
     */
    @Override
    public List<String> getRoleMenusList(UserDetail user,Long roleId) {
            return baseMapper.getRoleMenusList(roleId);
    }

    @Override
    public List<Long> getMenuIds(List<String> menus) {
        return baseMapper.getMenuIds(menus);
    }
}
