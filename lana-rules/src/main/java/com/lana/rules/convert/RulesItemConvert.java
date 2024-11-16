package com.lana.rules.convert;

import com.lana.rules.entity.RulesItemEntity;
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
public interface RulesItemConvert {
    RulesItemConvert INSTANCE = Mappers.getMapper(RulesItemConvert.class);


    RulesItemEntity convert(RulesItemSave saveVO);

    RulesItemEntity convert(RulesItemUpdate updateVO);
}
