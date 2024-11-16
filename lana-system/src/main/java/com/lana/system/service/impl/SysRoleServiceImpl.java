package com.lana.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.syshandle.exception.LanaException;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.system.convert.SysRoleConvert;
import com.lana.system.dao.SysRoleDao;
import com.lana.system.entity.SysRoleEntity;
import com.lana.system.entity.vo.query.SysRoleQuery;
import com.lana.system.entity.vo.result.SysRoleResult;
import com.lana.system.entity.vo.save.SysRoleSave;
import com.lana.system.entity.vo.update.SysRoleDataScopeUpdate;
import com.lana.system.entity.vo.update.SysRoleMenusUpdate;
import com.lana.system.entity.vo.update.SysRoleUpdate;
import com.lana.system.service.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/29 09:31
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    @Resource
    private SysRoleMenuService sysRoleMenuService;
    @Resource
    private SysUserTokenService sysUserTokenService;
    @Resource
    private SysMenusService sysMenusService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    /**
     * 角色分页查询
     *
     * @param query
     * @return
     */
    @Override
    public LanaPage<SysRoleResult> page(SysRoleQuery query) {
        IPage<SysRoleResult> page = baseMapper.getLists(getPage(query), query,true);
        return new LanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

    /**
     * 角色列表
     *
     * @param sysRoleQuery
     * @return
     */
    @Override
    public List<SysRoleResult> getList(SysRoleQuery sysRoleQuery) {
        List<SysRoleEntity> entityList = baseMapper.getList(sysRoleQuery,true);
        return SysRoleConvert.INSTANCE.convertList(entityList);
    }

    /**
     * 保存
     *
     * @param saveVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysRoleSave saveVO) {
            SysRoleEntity entity = SysRoleConvert.INSTANCE.convert(saveVO);
            baseMapper.insert(entity);
    }

    /**
     * 更新
     *
     * @param updateVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleUpdate updateVO) {
            SysRoleEntity entity = SysRoleConvert.INSTANCE.convert(updateVO);
            // 更新角色
            updateById(entity);
    }

    /**
     * 数据权限
     *
     * @param updateVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dataScope(SysRoleDataScopeUpdate updateVO) {
        try{
            SysRoleEntity entity = getById(updateVO.getId());
            entity.setDataScope(updateVO.getDataScope());
            // 更新角色
            updateById(entity);
            // 更新角色对应用户的缓存权限
            sysUserTokenService.updateCacheAuthByRoleId(entity.getId());
        }catch (Exception e){
            log.info("修改数据权限错误",e);
            throw new LanaException("修改数据权限错误：", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        try {
            // 删除角色
            removeByIds(idList);
            // 删除用户角色关系
            sysUserRoleService.deleteByRoleIdList(idList);
            // 删除角色菜单关系
            sysRoleMenuService.deleteByRoleIdList(idList);
            // 更新角色对应用户的缓存权限
            idList.forEach(sysUserTokenService::updateCacheAuthByRoleId);
        }catch (Exception e){
            log.info("删除角色错误",e);
            throw new LanaException("删除角色错误：", e);
        }
    }

    /**
     * 维护角色菜单
     * @param sysRoleMenusUpdate
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void roleLinkMenus(SysRoleMenusUpdate sysRoleMenusUpdate) {
        try {
            Long roleid = sysRoleMenusUpdate.getRoleId();
            //维护角色菜单
            List<Long> menuIdList = sysMenusService.getMenuIds(sysRoleMenusUpdate.getMenus());
            //简单粗暴，删除后再增加
            sysRoleMenuService.deleteByRoleIdList(Collections.singletonList(roleid));
            sysRoleMenuService.saveRoleMenu(roleid, menuIdList);
            //todo 维护控制台,后面再说
            //维护数据权限
            baseMapper.updateRole(roleid,sysRoleMenusUpdate.getGrid());
        }catch (Exception e){
            log.info("角色授权异常",e);
            throw new LanaException("角色授权异常：", e);
        }

    }
}
