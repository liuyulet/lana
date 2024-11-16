package com.lana.system.convert;

import com.lana.system.entity.SysUserTokenEntity;
import com.lana.system.entity.vo.result.SysUserTokenResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserTokenConvert {
    SysUserTokenConvert INSTANCE = Mappers.getMapper(SysUserTokenConvert.class);

    SysUserTokenEntity convert(SysUserTokenResult vo);

    SysUserTokenResult convert(SysUserTokenEntity entity);
}
