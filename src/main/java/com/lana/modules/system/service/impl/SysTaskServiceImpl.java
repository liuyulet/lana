package com.lana.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Query;
import com.lana.modules.system.pojo.entity.SysTaskEntity;
import com.lana.modules.system.dao.SysTaskDao;
import com.lana.modules.system.pojo.vo.LookCollaVO;
import com.lana.modules.system.service.SysTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SysTask)表服务实现类
 * @auther liuyulet
 * @since 2022-10-07 21:45:14
 */
@Service("sysTaskService")
public class SysTaskServiceImpl extends ServiceImpl<SysTaskDao, SysTaskEntity> implements SysTaskService {
    @Resource
    private SysTaskDao sysTaskDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<SysTaskEntity> page = this.page(
                new Query<SysTaskEntity>().getPage(params),
                new QueryWrapper<SysTaskEntity>()
                        .eq(params.get("userId") != null,"user_id", params.get("userId"))
                        .select().orderByDesc("create_time")
        );
        return new PageUtils(page);
    }


    @Override
    public List<LookCollaVO> getLookColla(String demanId) {
        return sysTaskDao.getLookColla(demanId);
    }
}
