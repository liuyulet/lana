package com.lana.system.convert;

import com.lana.system.entity.SysDictDataEntity;
import com.lana.system.entity.vo.result.SysDictDataResult;
import com.lana.system.entity.vo.save.SysDictDataSave;
import com.lana.system.entity.vo.update.SysDictDataUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDictDataConvert {

    SysDictDataConvert INSTANCE = Mappers.getMapper(SysDictDataConvert.class);

    SysDictDataResult convert(SysDictDataEntity entity);

    SysDictDataEntity convert(SysDictDataResult vo);

    SysDictDataEntity convert(SysDictDataSave vo);

    SysDictDataEntity convert(SysDictDataUpdate vo);


    List<SysDictDataResult> convertList(List<SysDictDataEntity> list);

}
