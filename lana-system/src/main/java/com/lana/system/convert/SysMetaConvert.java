package com.lana.system.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface SysMetaConvert {
    SysMetaConvert INSTANCE = Mappers.getMapper(SysMetaConvert.class);

}
