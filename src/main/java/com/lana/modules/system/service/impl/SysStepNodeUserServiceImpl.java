package com.lana.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lana.modules.system.dao.SysStepDao;
import com.lana.modules.system.pojo.entity.SysStep;
import com.lana.modules.system.pojo.entity.SysStepNodeUser;
import com.lana.modules.system.dao.SysStepNodeUserDao;
import com.lana.modules.system.service.SysStepNodeUserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysStepNodeUser)表服务实现类
 *
 * @author liuyulet
 * @since 2022-10-22 14:31:45
 */
@Service("sysStepNodeUserService")
public class SysStepNodeUserServiceImpl extends ServiceImpl<SysStepNodeUserDao, SysStepNodeUser> implements SysStepNodeUserService {
    @Resource
    private SysStepNodeUserDao sysStepNodeUserDao;


}
