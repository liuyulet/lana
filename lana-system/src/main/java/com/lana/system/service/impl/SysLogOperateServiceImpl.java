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


    @Value("${lana.logging-enabled:false}")
    private boolean loggingEnabled;

    @Resource
    private CacheOps cacheOps;

    @Override
    public LanaPage<SysLogOperateResult> page(SysLogOperateQuery query) {
        UserDetail user = SecurityUser.getUser();
        query.setUserId(user.getId());
        //如果开启了，则默认走td，否则，走mysql
        IPage<SysLogOperateResult> page = baseMapper.getListsTd(getPage(query),query);
        return new LanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }
    @PostConstruct
    public void saveLog() {
        if (loggingEnabled) {
            // todo 数据存储到redis中，批量消费，如果开启了TD，就将数据存入TDengine；如果不是，则默认存入mysql中，保证每一个操作都入库
            ScheduledExecutorService scheduled = ThreadUtil.createScheduledExecutor(1);
            scheduled.scheduleWithFixedDelay(() -> {
                try {
                    String key = CacheKeyBuilder.logKey();
                    StringBuffer sql = new StringBuffer();
                    sql.append("INSERT INTO");
                    // 每次插入10条
                    int count = 10;
                    for (int i = 0; i < count; i++) {
                        OptLogEntity log = (OptLogEntity) cacheOps.rightPop(key);
                        if (log != null) {
                            sql.append(" user");
                            sql.append(log.getUserId());
                            sql.append(" USING sys_log_operate TAGS(");
                            sql.append(log.getUserId());
                            sql.append(") values(");
                            sql.append("'");
                            sql.append(LocalDateTime.now());
                            sql.append("','");
                            sql.append(log.getModule());
                            sql.append("','");
                            sql.append(log.getName());
                            sql.append("','");
                            sql.append(log.getReqUri());
                            sql.append("','");
                            sql.append(log.getReqMethod());
                            sql.append("','");
                            sql.append(log.getReqParams());
                            sql.append("','");
                            sql.append(log.getIp());
                            sql.append("','");
                            sql.append(log.getUserAgent());
                            sql.append("',");
                            sql.append(log.getOperateType());
                            sql.append(",");
                            sql.append("1");
                            sql.append(",'");
                            sql.append(log.getRealName());
                            sql.append("')");
                        }
                    }
                    if(sql.length()>20){
                        baseMapper.insertTd(sql.toString());
                    }

                } catch (Exception e) {
                    log.error("日志存储失败", e);
                }
            }, 1, 10, TimeUnit.SECONDS);
        }
    }
}
