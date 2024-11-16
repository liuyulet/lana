package com.lana.system.service;

import com.lana.base.mybatis.service.BaseService;
import com.lana.system.entity.SysOrgEntity;
import com.lana.system.entity.vo.query.SysOrgQuery;
import com.lana.system.entity.vo.result.SysOrgResult;
import com.lana.system.entity.vo.save.SysOrgSave;
import com.lana.system.entity.vo.update.SysOrgUpdate;

import java.util.List;

public interface SysOrgService extends BaseService<SysOrgEntity> {

    /**
     * 根据机构ID，获取子机构ID列表(包含本机构ID)
     * @param id   机构ID
     */
    List<Long> getSubOrgIdList(Long id);

    /**
     * 列表查询
     * @return
     */
    List<SysOrgResult> getList(SysOrgQuery query);

    /**
     * 保存
     */
    void save(SysOrgSave saveVO);

    /**
     * 修改
     * @param updateVO
     */
    void update(SysOrgUpdate updateVO);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 根据用户所属角色，获取子机构ID列表(包含本机构ID)下面的用户的id
     * @param userId
     * @return
     */
    List<Long> getOrgIdList(Long userId);

    /**
     * 根据用户所属角色，查询出来本机构下面有哪些用户
     * @param userId
     * @return
     */
    List<Long> getOnlyOrgIdList(Long userId);
}
