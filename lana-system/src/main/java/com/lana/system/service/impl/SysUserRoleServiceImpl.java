package com.lana.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.system.dao.SysUserRoleDao;
import com.lana.system.entity.SysUserRoleEntity;
import com.lana.system.service.SysUserRoleService;
import com.lana.system.service.SysUserTokenService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther liuyulet
 * @date 2024/3/28 17:02
 */
@Slf4j
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

    @Resource
    private SysUserTokenService sysUserTokenService;

    @Override
    public List<Long> getRoleIdList(Long id) {
        return baseMapper.getRoleIdList(id);
    }

    /**
     * 按用户 ID 列表删除
     * @param idList
     */
    @Override
    public void deleteByUserIdList(List<Long> idList) {
        baseMapper.deleteByUserIdLists(idList);
        //remove(new LambdaQueryWrapper<SysUserRoleEntity>().in(SysUserRoleEntity::getUserId, idList));
    }

    @Override
    public void deleteByUserIdList(Long userId) {
        baseMapper.deleteByUserIdList(userId);
        //remove(new LambdaQueryWrapper<SysUserRoleEntity>().eq(SysUserRoleEntity::getUserId, userId));
    }

    @Override
    public void deleteByUserIdList(Long roleId, List<Long> userIdList) {
        LambdaQueryWrapper<SysUserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        remove(queryWrapper.eq(SysUserRoleEntity::getRoleId, roleId).in(SysUserRoleEntity::getUserId, userIdList));

        // 更新用户的缓存权限
        userIdList.forEach(sysUserTokenService::updateCacheAuthByUserId);
    }

    @Override
    public void deleteByRoleIdList(List<Long> roleIdList) {
        baseMapper.deleteByRoleIdList(roleIdList);
    }

    @Override
    public void saveUserList(Long roleId, List<Long> userIdList) {
        List<SysUserRoleEntity> list = userIdList.stream().map(userId -> {
            SysUserRoleEntity entity = new SysUserRoleEntity();
            entity.setUserId(userId);
            entity.setRoleId(roleId);
            return entity;
        }).collect(Collectors.toList());

        // 批量新增
        saveBatch(list);

        // 更新用户的缓存权限
        userIdList.forEach(sysUserTokenService::updateCacheAuthByUserId);
    }

    @Override
    public void saveUserRole(Long userId, List<Long> userRole) {
        baseMapper.saveUserRole(userId,userRole);
    }
}
