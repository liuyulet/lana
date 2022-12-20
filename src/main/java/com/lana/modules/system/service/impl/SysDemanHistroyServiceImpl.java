package com.lana.modules.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Query;
import com.lana.modules.system.dao.SysDemanHistroyDao;
import com.lana.modules.system.pojo.entity.SysDeman;
import com.lana.modules.system.pojo.entity.SysDemanHistroyEntity;
import com.lana.modules.system.service.SysDemanHistroyService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Map;

/**
 * 需求变更历史表(SysDemanHistroy)表服务实现类
 *
 * @author liuyulet
 * @since 2022-10-06 13:55:13
 */
@Service("sysDemanHistroyService")
public class SysDemanHistroyServiceImpl extends ServiceImpl<SysDemanHistroyDao, SysDemanHistroyEntity> implements SysDemanHistroyService {
    @Resource
    private SysDemanHistroyDao sysDemanHistroyDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String ids =  params.get("demandId").toString();
        IPage<SysDemanHistroyEntity> page = this.page(
                new Query<SysDemanHistroyEntity>().getPage(params),
                new QueryWrapper<SysDemanHistroyEntity>()
                        .eq(ids != null,"id", ids)
                        .select().orderByDesc("create_time")
        );
        return new PageUtils(page);
    }
}
