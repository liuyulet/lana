package com.lana.rules.service;

import com.lana.base.mybatis.service.BaseService;
import com.lana.rules.entity.RulesItemEntity;
import com.lana.rules.entity.vo.query.RulesItemQuery;
import com.lana.rules.entity.vo.result.RulesItemResult;
import com.lana.rules.entity.vo.save.RulesItemSave;
import com.lana.rules.entity.vo.update.RulesItemUpdate;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:30
 */
public interface RulesItemService extends BaseService<RulesItemEntity> {

    RulesItemResult getRules(RulesItemQuery queryVO);

    void updateAndSaveRulesItem(RulesItemUpdate updateVO);
}
