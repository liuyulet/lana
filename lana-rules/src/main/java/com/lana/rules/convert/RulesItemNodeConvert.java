package com.lana.rules.convert;

import com.lana.rules.entity.RulesItemEntity;
import com.lana.rules.entity.RulesItemNodeEntity;
import com.lana.rules.entity.vo.result.RulesItemNodeResult;
import com.lana.rules.entity.vo.save.RulesItemNodeSave;
import com.lana.rules.entity.vo.save.RulesItemSave;
import com.lana.rules.entity.vo.update.RulesItemUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/31 10:38
 */
@Mapper
public interface RulesItemNodeConvert {
    RulesItemNodeConvert INSTANCE = Mappers.getMapper(RulesItemNodeConvert.class);

    RulesItemNodeEntity convert(RulesItemNodeSave saveRulesItemNodeSaveVO);

    RulesItemNodeResult convert(RulesItemNodeEntity rulesItemNodeEntity);
}
