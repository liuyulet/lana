package com.lana.rules.service;

import com.lana.base.mybatis.service.BaseService;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.rules.entity.RulesItemEntity;
import com.lana.rules.entity.vo.query.RulesItemPageQuery;
import com.lana.rules.entity.vo.query.RulesItemQuery;
import com.lana.rules.entity.vo.result.RulesItemResult;
import com.lana.rules.entity.vo.save.RulesItemSave;
import com.lana.rules.entity.vo.update.RulesItemUpdate;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:30
 */
public interface RulesItemService extends BaseService<RulesItemEntity> {

    //RulesItemResult getRules(RulesItemQuery queryVO);
    void saveRulesItem(RulesItemSave saveVO);

    void updateRulesItem(RulesItemUpdate updateVO);

    /**
     * 该方法暂时弃用
     * @param
     */
    //void updateAndSaveRulesItem(RulesItemUpdate updateVO);

    LanaPage<RulesItemResult> getRulesPage(RulesItemPageQuery query);


    void deleteRulesItem(Long id);

}
