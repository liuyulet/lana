package com.lana.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.CacheOps;
import com.lana.base.operatelog.entity.OptLogEntity;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.base.security.token.user.SecurityUser;
import com.lana.base.security.token.user.UserDetail;
import com.lana.system.dao.SysLogOperateDao;
import com.lana.system.entity.SysLogOperateEntity;
import com.lana.system.entity.vo.query.SysLogOperateQuery;
import com.lana.system.entity.vo.result.SysLogOperateResult;
import com.lana.system.service.SysLogOperateService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * @auther liuyulet
 * @date 2024/3/30 17:14
 */
@Slf4j
@Service
public class SysLogOperateServiceImpl extends BaseServiceImpl<SysLogOperateDao, SysLogOperateEntity> implements SysLogOperateService {


    @Resource
    private CacheOps cacheOps;

    public LanaPage<SysLogOperateResult> page(SysLogOperateQuery query) {
        UserDetail user = SecurityUser.getUser();
        query.setUserId(user.getId());
        IPage<SysLogOperateResult> page = baseMapper.getLists(getPage(query),query);
        return new LanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

    @PostConstruct
    public void saveLog() {
        ScheduledExecutorService scheduledService = ThreadUtil.createScheduledExecutor(1);


        scheduledService.scheduleWithFixedDelay(() -> {
            try {
                String key = CacheKeyBuilder.logKey();
                // 每次插入10条
                int count = 10;
                for (int i = 0; i < count; i++) {
                    OptLogEntity log = (OptLogEntity) cacheOps.rightPop(key);
                    if (log == null) {
                        return;
                    }
                    SysLogOperateEntity entity = BeanUtil.copyProperties(log, SysLogOperateEntity.class);
                    baseMapper.insert(entity);
                }
            } catch (Exception e) {
                log.error("SysLogOperateServiceImpl.saveLog Error：",e);
            }
        }, 1, 10, TimeUnit.SECONDS);
    }
}
