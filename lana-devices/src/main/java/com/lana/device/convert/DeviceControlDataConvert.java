package com.lana.device.convert;

import com.lana.device.entity.DeviceControlDataEntity;
import com.lana.device.entity.DeviceProductTypeEntity;
import com.lana.device.entity.vo.save.DeviceControlDataSave;
import com.lana.device.entity.vo.save.DeviceProductTypeSave;
import com.lana.device.entity.vo.update.DeviceControlDataUpdate;
import com.lana.device.entity.vo.update.DeviceProductTypeUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/10/31 10:38
 */
@Mapper
public interface DeviceControlDataConvert {
    DeviceControlDataConvert INSTANCE = Mappers.getMapper(DeviceControlDataConvert.class);

    DeviceControlDataEntity convert(DeviceControlDataSave saveVO);

    DeviceControlDataEntity convert(DeviceControlDataUpdate updateVO);
}
