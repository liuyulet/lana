package com.lana.device.convert;

import com.lana.device.entity.DeviceEdgesItemEntity;
import com.lana.device.entity.DeviceGroupEntity;
import com.lana.device.entity.vo.result.DeviceEdgesItemResult;
import com.lana.device.entity.vo.save.DeviceEdgesItemSave;
import com.lana.device.entity.vo.save.DeviceGroupSave;
import com.lana.device.entity.vo.update.DeviceEdgesItemUpdate;
import com.lana.device.entity.vo.update.DeviceGroupUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/31 10:38
 */
@Mapper
public interface DeviceEdgesItemConvert {
    DeviceEdgesItemConvert INSTANCE = Mappers.getMapper(DeviceEdgesItemConvert.class);
    DeviceEdgesItemResult convert(DeviceEdgesItemEntity deviceEdgesItemEntity);
    DeviceEdgesItemEntity convert(DeviceEdgesItemSave saveVo);

    DeviceEdgesItemEntity convert(DeviceEdgesItemUpdate updateVo);
}


