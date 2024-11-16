package com.lana.system.service;

import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.mybatis.service.BaseService;
import com.lana.system.entity.SysDictDataEntity;
import com.lana.system.entity.vo.query.SysDictDataQuery;
import com.lana.system.entity.vo.result.SysDictDataResult;
import com.lana.system.entity.vo.save.SysDictDataSave;
import com.lana.system.entity.vo.update.SysDictDataUpdate;

import java.util.List;

/**
 * 数据字典
 */

public interface SysDictDataService  extends BaseService<SysDictDataEntity> {
    LanaPage<SysDictDataResult> page(SysDictDataQuery query);

    void save(SysDictDataSave saveVO);

    void update(SysDictDataUpdate updateVO);

    void delete(List<Long> idList);
}
