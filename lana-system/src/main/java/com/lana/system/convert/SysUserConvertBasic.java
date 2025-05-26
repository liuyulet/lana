package com.lana.system.convert;

import com.lana.system.entity.SysUserEntity;
import com.lana.system.entity.vo.result.SysUserResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SysUserConvertBasic {
    SysUserConvertBasic INSTANCE = Mappers.getMapper(SysUserConvertBasic.class);



    SysUserEntity convert(SysUserResult vo);


    List<SysUserResult> convertList(List<SysUserEntity> list);


}
