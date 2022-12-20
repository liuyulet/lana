package com.lana.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lana.modules.system.pojo.entity.SysTaskEntity;
import com.lana.modules.system.pojo.vo.LookCollaVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;


/**
 * (SysTask)表数据库访问层
 * @auther liuyulet
 * @since 2022-10-07 21:45:13
 */
@Mapper
public interface SysTaskDao extends BaseMapper<SysTaskEntity> {


    List<LookCollaVO> getLookColla(String demanId);
}

