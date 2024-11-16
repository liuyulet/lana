package com.lana.system.convert;

import com.lana.system.entity.SysMenuAllEntity;
import com.lana.system.entity.SysMenusEntity;
import com.lana.system.entity.vo.result.SysMenusResult;
import com.lana.system.entity.vo.save.SysMenusSave;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SysMenusConvert {
    SysMenusConvert INSTANCE = Mappers.getMapper(SysMenusConvert.class);


    List<SysMenusResult> convertMenuList(List<SysMenuAllEntity> menuList);

    SysMenusEntity convert(SysMenusSave saveVO);
}
