package com.lana.device.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.RedisCacheOps;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.base.syshandle.enums.GeneralPrefixEnum;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.device.convert.DeviceItemConvert;
import com.lana.device.dao.DeviceItemDao;
import com.lana.device.entity.DeviceItemEntity;
import com.lana.device.entity.vo.query.DeviceItemQuery;
import com.lana.device.entity.vo.query.GroupDeviceItemQuery;
import com.lana.device.entity.vo.result.DeviceItemGetResult;
import com.lana.device.entity.vo.result.DeviceItemResult;
import com.lana.device.entity.vo.result.DeviceProductModeResult;
import com.lana.device.entity.vo.result.GroupDeviceItemResult;
import com.lana.device.entity.vo.save.DeviceItemSave;
import com.lana.device.entity.vo.update.DeviceItemUpdate;
import com.lana.device.service.DeviceItemService;
import com.lana.device.service.DeviceModeService;
import com.lana.device.service.DeviceProductModeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:33
 */
@Slf4j
@Service
public class DeviceItemServiceImpl extends BaseServiceImpl<DeviceItemDao, DeviceItemEntity> implements DeviceItemService {
    @Resource
    private DeviceProductModeService deviceProductModeService;
    @Resource
    private DeviceModeService deviceModeService;
    @Resource
    private RedisCacheOps redisCacheOps;

    @Override
    public LanaPage<DeviceItemResult> getDeviceItemPage(DeviceItemQuery query) {

        IPage<DeviceItemGetResult> page = baseMapper.getDeviceItemPage(getPage(query),query,true);
        List<DeviceItemResult> deviceItemResults = page.getRecords().stream()
                .map(dto -> {
                    DeviceItemResult result = new DeviceItemResult();
                    // 复制基本属性...
                    result.setId(dto.getId());
                    result.setImgs(dto.getImgs());
                    result.setName(dto.getName());
                    result.setDeviceNumber(dto.getDeviceNumber());
                    result.setStatus(dto.getStatus());
                    result.setRemark(dto.getRemark());
                    result.setProductTypeId(dto.getProductTypeId());
                    result.setCreateTime(dto.getCreateTime());
                    result.setUpdateTime(dto.getUpdateTime());
                    result.setCreatorName(dto.getCreatorName());
                    result.setUpdaterName(dto.getUpdaterName());
                    result.setProductTypeShow(StringUtils.isNotBlank(dto.getProductTypeShow())
                            ? Arrays.stream(dto.getProductTypeShow().split(","))
                            .map(Long::valueOf)
                            .collect(Collectors.toList())
                            : Collections.emptyList());
                    return result;
                }).collect(Collectors.toList());

        return new LanaPage<>(deviceItemResults, page.getTotal(),page.getPages(),page.getSize());
    }



    @Override
    public void saveDeviceItem(DeviceItemSave vo) {

        DeviceItemEntity deviceItem = DeviceItemConvert.INSTANCE.convert(vo);
        Long lastElement = vo.getProductTypeShow().stream()
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElse(null);
        deviceItem.setProductTypeId(lastElement);
        StringBuilder sb = new StringBuilder();
        for (Long number : vo.getProductTypeShow()) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(number);
        }
        deviceItem.setProductTypeShow(sb.toString());
        baseMapper.insert(deviceItem);
        //判断是否继承产品的物模型
        if(vo.getProductTypeMode()){
            //检查所继承的产品是否有物模型
            List<DeviceProductModeResult> productModeList =deviceProductModeService.getProductModePage(deviceItem.getProductTypeId());
            if(productModeList!=null){
                //继承，则维护到设备表中
                deviceModeService.saveDeviceMode(productModeList,deviceItem.getId());
                //生成设备子表，用于存储设备数据
                String sql = assembleTheSQL(productModeList,deviceItem.getId());
                //创建子表
                baseMapper.createTdTable(sql);
                String key = CacheKeyBuilder.deviceMode(GeneralPrefixEnum.TABLE_PREFIX.getValue() + deviceItem.getId());
                //创建物模型数据结构
                Object data = redisCacheOps.get(key);
                List<String> objects = new ArrayList<>();
                for (DeviceProductModeResult productModeResult:productModeList) {
                    objects.add(productModeResult.getModeSigns());
                }
                if(data!=null){
                    redisCacheOps.delete(key);
                    redisCacheOps.set(key,objects);
                }else {
                    redisCacheOps.set(key,objects);
                }
            }
        }
    }



    @Override
    public void updateByUserId(DeviceItemUpdate vo) {
        DeviceItemEntity deviceItem = DeviceItemConvert.INSTANCE.convert(vo);
        Long lastElement = vo.getProductTypeShow().stream()
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElse(null);
        deviceItem.setProductTypeId(lastElement);
        StringBuilder sb = new StringBuilder();
        for (Long number : vo.getProductTypeShow()) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(number);
        }
        deviceItem.setProductTypeShow(sb.toString());
        baseMapper.updateById(deviceItem);
    }

    @Override
    public void deleteDeviceItem(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public LanaPage<GroupDeviceItemResult> groupDeviceItemPage(GroupDeviceItemQuery query) {
        IPage<GroupDeviceItemResult> page = baseMapper.groupDeviceItemPage(getPage(query),query,true);
        return new LanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

    @Override
    public List<GroupDeviceItemResult> groupDeviceItemList(Long groupId) {
        return  baseMapper.groupDeviceItemList(groupId);
    }

    @Override
    public long getByDeviceType(Long id) {
        //查询所属该产品的设备
        return baseMapper.getByDeviceType(id);
    }


    /**
     * 组装生成sql
     * @param productModeList
     * @return
     */
    private String assembleTheSQL(List<DeviceProductModeResult> productModeList,long deviceId) {
        //组装普通表sql
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE sb");
        sb.append(deviceId);
        sb.append("(ts timestamp,");
        for (int i = 0; i < productModeList.size(); i++) {
            sb.append(productModeList.get(i).getModeSigns());
            sb.append(" ");
            sb.append(productModeList.get(i).getModeDataType());
            if (i < productModeList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(");");
        return sb.toString();
    };
}
