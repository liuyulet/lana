package com.lana.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Query;
import com.lana.modules.system.dao.SysUserDepartRoleDao;
import com.lana.modules.system.pojo.entity.PalnItemEntity;
import com.lana.modules.system.dao.PalnItemDao;
import com.lana.modules.system.pojo.entity.SysUserDepartRoleEntity;
import com.lana.modules.system.service.PalnItemService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (PalnItem)表服务实现类
 *
 * @author liuyulet
 * @since 2022-11-03 15:16:13
 */
@Service("palnItemService")
public class PalnItemServiceImpl extends ServiceImpl<PalnItemDao, PalnItemEntity> implements PalnItemService {
    @Resource
    private PalnItemDao palnItemDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<PalnItemEntity> page = this.page(
                new Query<PalnItemEntity>().getPage(params),
                new QueryWrapper<PalnItemEntity>()
                .eq(params.get("planDemanId") != null,"plan_deman_id", params.get("planDemanId"))
        );
        return new PageUtils(page);
    }
}
