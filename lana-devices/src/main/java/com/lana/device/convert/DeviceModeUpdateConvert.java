package com.lana.device.convert;

import com.lana.device.entity.DeviceModeEntity;
import com.lana.device.entity.vo.update.DeviceModeUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface DeviceModeUpdateConvert {
    DeviceModeUpdateConvert INSTANCE = Mappers.getMapper(DeviceModeUpdateConvert.class);

    DeviceModeEntity convert(DeviceModeUpdate updataVO);
}
