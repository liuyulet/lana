package com.lana.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lana.modules.system.dao.SysStepDao;
import com.lana.modules.system.pojo.entity.SysStep;
import com.lana.modules.system.pojo.entity.SysStepNode;
import com.lana.modules.system.dao.SysStepNodeDao;
import com.lana.modules.system.service.SysStepNodeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysStepNode)表服务实现类
 *
 * @author liuyulet
 * @since 2022-10-22 14:31:45
 */
@Service("sysStepNodeService")
public class SysStepNodeServiceImpl extends ServiceImpl<SysStepNodeDao, SysStepNode> implements SysStepNodeService {
    @Resource
    private SysStepNodeDao sysStepNodeDao;


}
