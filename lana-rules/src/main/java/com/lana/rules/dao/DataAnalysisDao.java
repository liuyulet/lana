package com.lana.rules.dao;


import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:34
 */
@Mapper
public interface DataAnalysisDao {
    @DS("tdengine")
    void save( @Param("sql")String sql);

}
