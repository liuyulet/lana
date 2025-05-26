package com.lana.rules.service;


import com.lana.base.mybatis.service.BaseService;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.rules.entity.RulesItemNodeEntity;
import com.lana.rules.entity.vo.query.RulesItemNodeQuery;
import com.lana.rules.entity.vo.save.RulesItemNodeSave;
import com.lana.rules.entity.vo.result.RulesItemNodeResult;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:30
 */
public interface RulesItemNodeService extends BaseService<RulesItemNodeEntity> {


    LanaResult SaveAndUpdate(RulesItemNodeSave saveRulesItemNodeSaveVO);

    RulesItemNodeResult GetRulesItemDetail(RulesItemNodeQuery queryVO);
}
