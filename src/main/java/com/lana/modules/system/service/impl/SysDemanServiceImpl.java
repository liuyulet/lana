package com.lana.modules.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Query;
import com.lana.modules.system.dao.SysDemanDao;
import com.lana.modules.system.pojo.entity.SysDeman;
import com.lana.modules.system.pojo.entity.SysUserEntity;
import com.lana.modules.system.service.SysDemanService;
import com.lana.modules.system.service.SysUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;


/**
 * (SysDeman)表服务实现类
 *
 * @author liuyulet
 * @since 2022-09-30 11:25:42
 */
@Service("sysDemanService")
public class SysDemanServiceImpl extends ServiceImpl<SysDemanDao, SysDeman> implements SysDemanService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<SysDeman> page = this.page(
                new Query<SysDeman>().getPage(params),
                new QueryWrapper<SysDeman>()
                .select().orderByDesc("create_time")
        );
        return new PageUtils(page);
    }
}
