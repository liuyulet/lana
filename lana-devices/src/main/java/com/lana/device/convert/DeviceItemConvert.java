package com.lana.device.convert;

import com.lana.device.entity.DeviceItemEntity;
import com.lana.device.entity.vo.save.DeviceItemSave;
import com.lana.device.entity.vo.update.DeviceItemUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/31 10:38
 */
@Mapper
public interface DeviceItemConvert {
    DeviceItemConvert INSTANCE = Mappers.getMapper(DeviceItemConvert.class);
    @Mapping(target = "productTypeShow", ignore = true)
    DeviceItemEntity convert(DeviceItemSave vo);
    @Mapping(target = "productTypeShow", ignore = true)
    DeviceItemEntity convert(DeviceItemUpdate vo);

}
