package com.lana.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lana.modules.system.pojo.entity.SysDeman;
import com.lana.modules.system.pojo.entity.SysDepart;
import com.lana.modules.system.pojo.entity.SysUserEntity;
import com.lana.modules.system.pojo.vo.SysDemanVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SysDeman)表数据库访问层
 *
 * @author liuyulet
 * @since 2022-09-30 11:25:38
 */
@Mapper
public interface SysDemanDao extends BaseMapper<SysDeman> {

    void userForDeman(@Param("demendId")Integer demendId, @Param("userList")List<Integer> userList,@Param("userName") String userName);

    IPage<SysDemanVO> getPageData(@Param("page") Page<HashMap<String, Object>> page, @Param("userEntit") SysUserEntity userEntit, @Param("params")Map<String, String> params);
}

