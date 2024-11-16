package com.lana.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lana.base.syshandle.exception.LanaException;
import com.lana.base.utils.TreeUtils;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.system.convert.SysOrgConvert;
import com.lana.system.dao.SysOrgDao;
import com.lana.system.dao.SysUserDao;
import com.lana.system.entity.SysOrgEntity;
import com.lana.system.entity.vo.query.SysOrgQuery;
import com.lana.system.entity.vo.result.SysOrgResult;
import com.lana.system.entity.vo.save.SysOrgSave;
import com.lana.system.entity.vo.update.SysOrgUpdate;
import com.lana.system.service.SysOrgService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/19 14:00
 */
@Slf4j
@Service
public class SysOrgServiceImpl extends BaseServiceImpl<SysOrgDao, SysOrgEntity> implements SysOrgService {

    @Resource
    private SysUserDao sysUserDao;

    /**
     * 根据机构ID，获取子机构ID列表(包含本机构ID)
     * @param id   机构ID
     * @return
     */
    @Override
    public List<Long> getSubOrgIdList(Long id) {
        // 所有机构的id、pid列表
        List<SysOrgEntity> orgList = baseMapper.getIdAndPidList();
        // 递归查询所有子机构ID列表
        List<Long> subIdList = new ArrayList<>();
        getTree(id, orgList, subIdList);
        // 本机构也添加进去
        subIdList.add(id);
        return subIdList;
    }

    /**
     * 列表处理
     * @return
     */
    @Override
    public List<SysOrgResult> getList(SysOrgQuery query) {
        // 机构列表
        List<SysOrgResult> entityList = baseMapper.getList(query,true);
        return TreeUtils.build(entityList);
    }

    /**
     * 新增保存
     * @param saveVO
     */
    @Override
    public void save(SysOrgSave saveVO) {
        SysOrgEntity entity = SysOrgConvert.INSTANCE.convert(saveVO);
        baseMapper.insert(entity);
    }

    /**
     * 更新
     * @param updateVO
     */
    @Override
    public void update(SysOrgUpdate updateVO) {
        SysOrgEntity entity = SysOrgConvert.INSTANCE.convert(updateVO);
        // 上级机构不能为自身
        if (entity.getId().equals(entity.getPid())) {
            throw new LanaException("上级机构不能为自身");
        }
        // 上级机构不能为下级
        List<Long> subOrgList = getSubOrgIdList(entity.getId());
        if (subOrgList.contains(entity.getPid())) {
            throw new LanaException("上级机构不能为下级");
        }
        updateById(entity);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        // 判断是否有子机构
        long orgCount = count(new QueryWrapper<SysOrgEntity>().eq("pid", id));
        if (orgCount > 0) {
            throw new LanaException("请先删除子机构");
        }
        // todo 判断机构下面是否有用户
/*        long userCount = sysUserDao.selectCount(new QueryWrapper<SysUserEntity>().eq("org_id", id));
        if (userCount > 0) {
            throw new ServerException("机构下面有用户，不能删除");
        }*/

        // 删除
        removeById(id);
    }

    @Override
    public List<Long> getOrgIdList(Long userId) {
        Long orgId = baseMapper.getByUserId(userId);
        // 所有机构的id、pid列表
        List<SysOrgEntity> orgList = baseMapper.getIdAndPidList();
        // 递归查询所有子机构ID列表
        List<Long> subIdList = new ArrayList<>();
        getTree(orgId, orgList, subIdList);
        // 本机构也添加进去
        subIdList.add(orgId);

        //查询出来所有的用户
        List<Long> userIds = sysUserDao.getUSerIds(subIdList);

        return userIds;
    }

    @Override
    public List<Long> getOnlyOrgIdList(Long userId) {
        Long orgId = baseMapper.getByUserId(userId);
        List<Long> userIds = sysUserDao.getOnlyOrgIdList(orgId);
        return userIds;
    }

    private void getTree(Long id, List<SysOrgEntity> orgList, List<Long> subIdList) {
        for (SysOrgEntity org : orgList) {
            if (ObjectUtil.equals(org.getPid(), id)) {
                getTree(org.getId(), orgList, subIdList);
                subIdList.add(org.getId());
            }
        }
    }
}
