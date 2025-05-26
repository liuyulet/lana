package com.lana.rules.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.rules.convert.RulesItemConvert;
import com.lana.rules.dao.RulesItemDao;
import com.lana.rules.entity.RulesItemEntity;
import com.lana.rules.entity.vo.query.RulesItemPageQuery;
import com.lana.rules.entity.vo.query.RulesItemQuery;
import com.lana.rules.entity.vo.result.RulesItemResult;
import com.lana.rules.entity.vo.save.RulesItemSave;
import com.lana.rules.entity.vo.update.RulesItemUpdate;
import com.lana.rules.service.RulesItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:35
 */
@Slf4j
@Service
public class RulesItemServiceIpml extends BaseServiceImpl<RulesItemDao, RulesItemEntity> implements RulesItemService {

    @Value("${lana.aviator-path}")
    private String aviatorPath;


    @Override
    public LanaPage<RulesItemResult> getRulesPage(RulesItemPageQuery query) {
        IPage<RulesItemResult> page = baseMapper.getRulesPage(getPage(query), query,true);
        return new LanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }


/*    @Override
    public RulesItemResult getRules(RulesItemQuery queryVO) {

        RulesItemResult rulesItemResult = baseMapper.getRules(queryVO);

        return rulesItemResult;
    }*/

    @Override
    public void saveRulesItem(RulesItemSave saveVO) {
        RulesItemEntity rulesItemEntity = RulesItemConvert.INSTANCE.convert(saveVO);
        baseMapper.insert(rulesItemEntity);
    }

    @Override
    public void updateRulesItem(RulesItemUpdate updateVO) {
        RulesItemEntity rulesItemEntity = RulesItemConvert.INSTANCE.convert(updateVO);
        baseMapper.updateById(rulesItemEntity);
    }

    @Override
    public void deleteRulesItem(Long id) {
        baseMapper.deleteById(id);
        baseMapper.deleteRulesItemNode(id);
    }


}
