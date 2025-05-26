package com.lana.system.convert;

import com.lana.system.entity.SysRoleEntity;
import com.lana.system.entity.vo.result.SysRoleResult;
import com.lana.system.entity.vo.save.SysRoleSave;
import com.lana.system.entity.vo.update.SysRoleUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysRoleConvert {
    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

    SysRoleResult convert(SysRoleEntity entity);

    SysRoleEntity convert(SysRoleResult vo);

    SysRoleEntity convert(SysRoleSave saveVO);

    List<SysRoleResult> convertList(List<SysRoleEntity> entityList);

    SysRoleEntity convert(SysRoleUpdate updateVO);
}
