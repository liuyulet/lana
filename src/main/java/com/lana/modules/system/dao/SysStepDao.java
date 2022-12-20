package com.lana.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lana.modules.system.pojo.entity.SysStep;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * (SysStep)表数据库访问层
 *
 * @author liuyulet
 * @since 2022-10-22 14:31:42
 */
@Mapper
public interface SysStepDao  extends BaseMapper<SysStep> {

    void saveSysStep(@Param("sysStep") SysStep sysStep);

    void savess(@Param("value")String value, @Param("users")int[] users, @Param("stepId")Long stepId);

    List<HashMap<String, Object>> getByStep(Long stepId);

    List<HashMap<String, Object>> getByStepPeople(Long stepId);

    void delStepNode(Long stepId);

    void delStepNodeUser(Long stepId);

    List<Long> getUserId(@Param("stepId")Object stepId, @Param("stepNode")Object stepNode);
}

