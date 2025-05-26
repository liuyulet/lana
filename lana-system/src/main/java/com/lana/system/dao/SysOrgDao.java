package com.lana.system.dao;

import com.lana.base.mybatis.dao.BaseDao;
import com.lana.system.entity.SysOrgEntity;
import com.lana.system.entity.vo.query.SysOrgQuery;
import com.lana.system.entity.vo.result.SysOrgResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysOrgDao extends BaseDao<SysOrgEntity> {


    /**
     * 获取所有机构的id、pid列表
     */
    List<SysOrgEntity> getIdAndPidList();



    /**
     * 列表查询
     * @param
     * @return
     */
    List<SysOrgResult> getList(@Param("params") SysOrgQuery params,@Param("DataScopeIgnore") boolean DataScopeIgnore);

    Long getByUserId(@Param("userId") Long userId);
}
