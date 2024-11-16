package com.lana.device.convert;

import com.lana.device.entity.DeviceProductTypeEntity;
import com.lana.device.entity.vo.save.DeviceProductTypeSave;
import com.lana.device.entity.vo.update.DeviceProductTypeUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/31 10:38
 */
@Mapper
public interface DeviceProductTypeConvert {
    DeviceProductTypeConvert INSTANCE = Mappers.getMapper(DeviceProductTypeConvert.class);

    DeviceProductTypeEntity convert(DeviceProductTypeSave saveVO);
    DeviceProductTypeEntity convert(DeviceProductTypeUpdate saveVO);
}
