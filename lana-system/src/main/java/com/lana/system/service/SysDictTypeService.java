package com.lana.system.service;

import com.lana.base.mybatis.service.BaseService;
import com.lana.system.entity.SysDictTypeEntity;
import com.lana.system.entity.vo.save.SysDictTypeSave;

import java.util.List;

public interface SysDictTypeService extends BaseService<SysDictTypeEntity> {

    /**
     * 保存
     * @param saveVO
     */
    void save(SysDictTypeSave saveVO);

    /**
     * 删除
     * @param idList
     */
    void delete(List<Long> idList);


    List<SysDictTypeEntity> getList();

    void removeDictType(Long id);
}
