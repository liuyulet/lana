package com.lana.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.device.convert.DeviceEdgesItemConvert;
import com.lana.device.dao.DeviceEdgesItemDao;
import com.lana.device.entity.DeviceEdgesItemEntity;
import com.lana.device.entity.vo.query.DeviceEdgesQuery;
import com.lana.device.entity.vo.result.DeviceEdgesItemResult;
import com.lana.device.entity.vo.result.EdgesItemDataResult;
import com.lana.device.entity.vo.save.DeviceEdgesItemSave;
import com.lana.device.entity.vo.update.DeviceEdgesItemUpdate;
import com.lana.device.service.DeviceEdgesItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/10/25 16:54
 */
@Slf4j
@Service
public class DeviceEdgesItemServiceImpl  extends BaseServiceImpl<DeviceEdgesItemDao, DeviceEdgesItemEntity> implements DeviceEdgesItemService {


    @Override
    public LanaPage<DeviceEdgesItemResult> getDeviceEdgesItemPage(DeviceEdgesQuery deviceEdgesQuery) {
        IPage<DeviceEdgesItemResult> page = baseMapper.getDeviceEdgesItemPage(getPage(deviceEdgesQuery), deviceEdgesQuery,true);
        return new LanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

    @Override
    public DeviceEdgesItemEntity saveDeviceEdgesItem(DeviceEdgesItemSave saveVo) {
        baseMapper.delEdgesItem(saveVo.getEdgeProductTypeId());
        DeviceEdgesItemEntity deviceEdgesItemEntity = DeviceEdgesItemConvert.INSTANCE.convert(saveVo);
        baseMapper.insert(deviceEdgesItemEntity);
        return deviceEdgesItemEntity;
    }

    @Override
    public DeviceEdgesItemEntity updateDeviceEdgesItem(DeviceEdgesItemUpdate updateVo) {
        DeviceEdgesItemEntity deviceEdgesItemEntity = DeviceEdgesItemConvert.INSTANCE.convert(updateVo);
        baseMapper.updateById(deviceEdgesItemEntity);
        return deviceEdgesItemEntity;
    }

    @Override
    public DeviceEdgesItemResult getEdgesItemByProductTypeId(Long id) {
        DeviceEdgesItemEntity deviceEdgesItemEntity = baseMapper.selectOne(new QueryWrapper<DeviceEdgesItemEntity>().eq("edge_product_type_id", id).eq("deleted",0));
        DeviceEdgesItemResult deviceEdgesItemResult = DeviceEdgesItemConvert.INSTANCE.convert(deviceEdgesItemEntity);
        return deviceEdgesItemResult;
    }

    @Override
    public List<EdgesItemDataResult> getEdgesItemEdge(DeviceEdgesItemResult deviceEdgesItemResult) {
        return baseMapper.getEdgesItemEdge();
    }

    @Override
    public void delEdgesItem(String productTypeId) {
        baseMapper.delEdgesItem(productTypeId);
    }

}
