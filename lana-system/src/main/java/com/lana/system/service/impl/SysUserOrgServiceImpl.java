package com.lana.system.service.impl;

import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.system.dao.SysUserOrgDao;
import com.lana.system.entity.SysUserOrgEntity;
import com.lana.system.service.SysUserOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/28 17:02
 */
@Slf4j
@Service
public class SysUserOrgServiceImpl extends BaseServiceImpl<SysUserOrgDao, SysUserOrgEntity> implements SysUserOrgService {

    @Override
    public void saveUserOrg(Long userId, List<Long> userOrg) {
        baseMapper.saveUserOrg(userId,userOrg);
    }

    @Override
    public void deleteByUserIdList(List<Long> idList) {
        baseMapper.deleteByUserIdLists(idList);
        //remove(new LambdaQueryWrapper<SysUserOrgEntity>().in(SysUserOrgEntity::getUserId, idList));
    }

    @Override
    public void deleteByUserIdList(Long userId) {
        baseMapper.deleteByUserIdList(userId);
        //remove(new LambdaQueryWrapper<SysUserOrgEntity>().eq(SysUserOrgEntity::getUserId, userId));
    }
}
