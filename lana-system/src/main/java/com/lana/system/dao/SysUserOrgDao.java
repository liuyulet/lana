package com.lana.system.dao;

import com.lana.base.mybatis.dao.BaseDao;
import com.lana.system.entity.SysUserOrgEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserOrgDao extends BaseDao<SysUserOrgEntity> {

    void saveUserOrg(@Param("userId") Long userId,@Param("userOrg") List<Long> userOrg);

    void deleteByUserIdLists(List<Long> idList);

    void deleteByUserIdList(Long userId);
}
