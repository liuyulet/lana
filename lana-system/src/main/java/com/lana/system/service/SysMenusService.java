package com.lana.system.service;

import com.lana.base.mybatis.service.BaseService;
import com.lana.base.security.token.user.UserDetail;
import com.lana.system.entity.SysMenusEntity;
import com.lana.system.entity.vo.result.SysMenusNavResult;
import com.lana.system.entity.vo.result.SysMenusResult;
import com.lana.system.entity.vo.save.SysMenusSave;
import com.lana.system.entity.vo.update.SysMenusUpdate;

import java.util.List;
import java.util.Set;

public interface SysMenusService extends BaseService<SysMenusEntity> {

    /**
     * nav菜单导航
     * @param user
     * @return
     */
    SysMenusNavResult getNavMenuList(UserDetail user);

    /**
     * 菜单列表
     * @param user
     * @return
     */
    List<SysMenusResult> getMenuList(UserDetail user);

    /**
     * 增加菜单
     * @param saveVO
     */
    SysMenusEntity saveMenu(SysMenusSave saveVO);

    /**
     * 修改
     * @param uodateVO
     */
    void updateByData(SysMenusUpdate uodateVO);

    /**
     * 删除代码
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 获取用户权限列表
     * @param userDetail
     * @return
     */
    Set<String> getUserAuthority(UserDetail userDetail);

    /**
     * 角色菜单
     * @param roleId
     * @return
     */
    List<String> getRoleMenusList(UserDetail user,Long roleId);

    /**
     * 查询角色菜单id
     * @param menus
     * @return
     */
    List<Long> getMenuIds(List<String> menus);
}
