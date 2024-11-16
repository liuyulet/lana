package com.lana.system.convert;

import com.lana.system.entity.SysOrgEntity;
import com.lana.system.entity.vo.result.SysOrgResult;
import com.lana.system.entity.vo.save.SysOrgSave;
import com.lana.system.entity.vo.update.SysOrgUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysOrgConvert {
    SysOrgConvert INSTANCE = Mappers.getMapper(SysOrgConvert.class);

    SysOrgEntity convert(SysOrgResult vo);

    SysOrgEntity convert(SysOrgSave saveVO);

    SysOrgEntity convert(SysOrgUpdate updateVO);

    SysOrgResult convert(SysOrgEntity entity);

    List<SysOrgResult> convertList(List<SysOrgEntity> list);

}