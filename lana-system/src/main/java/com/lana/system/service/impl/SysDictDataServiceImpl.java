package com.lana.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.system.convert.SysDictDataConvert;
import com.lana.system.dao.SysDictDataDao;
import com.lana.system.entity.SysDictDataEntity;
import com.lana.system.entity.vo.query.SysDictDataQuery;
import com.lana.system.entity.vo.result.SysDictDataResult;
import com.lana.system.entity.vo.save.SysDictDataSave;
import com.lana.system.entity.vo.update.SysDictDataUpdate;
import com.lana.system.service.SysDictDataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/31 22:34
 */
@Service
public class SysDictDataServiceImpl extends BaseServiceImpl<SysDictDataDao, SysDictDataEntity> implements SysDictDataService {


    @Override
    public LanaPage<SysDictDataResult> page(SysDictDataQuery query) {
        IPage<SysDictDataResult> page = baseMapper.getLists(getPage(query),query);
        return new LanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

    @Override
    public void save(SysDictDataSave saveVO) {
        SysDictDataEntity entity = SysDictDataConvert.INSTANCE.convert(saveVO);

        baseMapper.insert(entity);
    }

    @Override
    public void update(SysDictDataUpdate updateVO) {
        SysDictDataEntity entity = SysDictDataConvert.INSTANCE.convert(updateVO);

        updateById(entity);
    }

    @Override
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }
}
