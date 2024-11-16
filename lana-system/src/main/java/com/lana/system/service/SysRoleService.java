package com.lana.system.service;

import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.mybatis.service.BaseService;
import com.lana.system.entity.SysRoleEntity;
import com.lana.system.entity.vo.query.SysRoleQuery;
import com.lana.system.entity.vo.result.SysRoleResult;
import com.lana.system.entity.vo.save.SysRoleSave;
import com.lana.system.entity.vo.update.SysRoleDataScopeUpdate;
import com.lana.system.entity.vo.update.SysRoleMenusUpdate;
import com.lana.system.entity.vo.update.SysRoleUpdate;

import java.util.List;

public interface SysRoleService extends BaseService<SysRoleEntity> {

    LanaPage<SysRoleResult> page(SysRoleQuery query);

    List<SysRoleResult> getList(SysRoleQuery sysRoleQuery);

    void save(SysRoleSave saveVO);

    void update(SysRoleUpdate updateVO);

    void dataScope(SysRoleDataScopeUpdate updateVO);

    void delete(List<Long> idList);

    void roleLinkMenus(SysRoleMenusUpdate sysRoleMenusUpdate);
}
