package com.lana.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Query;
import com.lana.modules.system.dao.SysProjectDao;
import com.lana.modules.system.pojo.entity.SysProjectEntity;
import com.lana.modules.system.service.SysProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * (SysProject)表服务实现类
 *
 * @author liuyulet
 * @since 2022-10-06 11:23:37
 */
@Service("sysProjectService")
public class SysProjectServiceImpl  extends ServiceImpl<SysProjectDao, SysProjectEntity> implements SysProjectService {
    @Autowired
    private SysProjectDao sysProjectDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysProjectEntity> page = this.page(
                new Query<SysProjectEntity>().getPage(params),
                new QueryWrapper<SysProjectEntity>()
                        .select().orderByDesc("create_time")
        );
        return new PageUtils(page);
    }

    @Override
    public SysProjectEntity getProjects(String demanProject) {
        return sysProjectDao.getProjects(demanProject);
    }
}
