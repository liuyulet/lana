package com.lana.system.convert;

import com.lana.system.entity.SysDictTypeEntity;
import com.lana.system.entity.vo.result.SysDictTypeResult;
import com.lana.system.entity.vo.save.SysDictTypeSave;
import com.lana.system.entity.vo.update.SysDictTypeUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 系统字典类型转换器
 */
@Mapper
public interface SysDictTypeConvert {



        // 单例模式获取转换器实例
        SysDictTypeConvert INSTANCE = Mappers.getMapper(SysDictTypeConvert.class);

        /**
         * 将字典类型实体转换为字典类型结果对象
         *
         * @param entity 字典类型实体
         * @return 转换后的字典类型结果对象
         */
        SysDictTypeResult convert(SysDictTypeEntity entity);

        /**
         * 将字典类型结果对象转换为字典类型实体
         *
         * @param vo 字典类型结果对象
         * @return 转换后的字典类型实体
         */
        SysDictTypeEntity convert(SysDictTypeResult vo);

        /**
         * 将保存字典类型的请求对象转换为字典类型实体
         *
         * @param vo 保存字典类型的请求对象
         * @return 转换后的字典类型实体
         */
        SysDictTypeEntity convert(SysDictTypeSave vo);

        /**
         * 将更新字典类型的请求对象转换为字典类型实体
         *
         * @param vo 更新字典类型的请求对象
         * @return 转换后的字典类型实体
         */
        SysDictTypeEntity convert(SysDictTypeUpdate vo);

        /**
         * 将字典类型实体列表转换为字典类型结果对象列表
         *
         * @param list 字典类型实体列表
         * @return 转换后的字典类型结果对象列表
         */
        List<SysDictTypeResult> convertList(List<SysDictTypeEntity> list);


}
