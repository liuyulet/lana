package com.lana.device.convert;

import com.lana.device.entity.DeviceGroupEntity;
import com.lana.device.entity.DeviceItemEntity;
import com.lana.device.entity.vo.save.DeviceGroupSave;
import com.lana.device.entity.vo.save.DeviceItemSave;
import com.lana.device.entity.vo.update.DeviceGroupUpdate;
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
public interface DeviceGroupConvert {
    DeviceGroupConvert INSTANCE = Mappers.getMapper(DeviceGroupConvert.class);

    DeviceGroupEntity convert(DeviceGroupSave saveVO);
    DeviceGroupEntity convert(DeviceGroupUpdate updateVo);

}
