package com.lana.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lana.modules.system.pojo.entity.SysDemanUserEntity;
import com.lana.modules.system.pojo.entity.SysUserEntity;
import com.lana.modules.system.pojo.vo.TaskUserDataVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * (SysDemanUser)表数据库访问层
 * @auther liuyulet
 * @since 2022-10-07 23:16:34
 */
@Mapper
public interface SysDemanUserDao extends BaseMapper<SysDemanUserEntity> {


    List<HashMap<String, Object>> getAllOver(String demanId);

    IPage<TaskUserDataVO> getPageData(@Param("page") Page<HashMap<String, Object>> page, @Param("userEntit") SysUserEntity userEntit,@Param("params") Map<String, String> params);

    void deleteUserTask(Long itemDd);

    void deleteBinding(Long itemDd);

    List<SysDemanUserEntity> selectdata(Long palnItemId);

    void overUpdate(@Param("palnItemId") Long palnItemId, @Param("stepId") Long stepId);

    void nextUpdate(@Param("nodeNext") int nodeNext, @Param("stepId") Long stepId, @Param("palnItemId") Long palnItemId);

    void backUpdate(@Param("palnItemId") Long palnItemId, @Param("stepId") Long stepId);
}

