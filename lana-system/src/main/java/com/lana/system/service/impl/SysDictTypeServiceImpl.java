package com.lana.system.service.impl;

import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.system.convert.SysDictTypeConvert;
import com.lana.system.dao.SysDictDataDao;
import com.lana.system.dao.SysDictTypeDao;
import com.lana.system.entity.SysDictTypeEntity;
import com.lana.system.entity.vo.save.SysDictTypeSave;
import com.lana.system.service.SysDictTypeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther liuyulet
 * @date 2024/3/31 17:55
 */
@Slf4j
@Service
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictTypeDao, SysDictTypeEntity> implements SysDictTypeService {
    @Resource
    private SysDictDataDao sysDictDataDao;
    @Override
    public void save(SysDictTypeSave saveVO) {
        SysDictTypeEntity entity = SysDictTypeConvert.INSTANCE.convert(saveVO);
        baseMapper.insert(entity);
    }
    @Override
    public void delete(List<Long> idList) {

        removeByIds(idList);
    }

    @Override
    public List<SysDictTypeEntity> getList() {

        return baseMapper.getList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeDictType(Long id) {
        //先清空字典子项数据
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("dic", id);
        sysDictDataDao.deleteByMap(columnMap);
        //在清空字典类型
        baseMapper.deleteById(id);
    }


}
