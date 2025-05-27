package com.lana.system.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.RedisCacheOps;
import com.lana.base.operatelog.entity.OptLogEntity;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.base.security.token.user.SecurityUser;
import com.lana.base.security.token.user.UserDetail;
import com.lana.system.dao.SysLogOperateDao;
import com.lana.system.entity.SysLogOperateEntity;
import com.lana.system.entity.vo.query.SysLogOperateQuery;
import com.lana.system.entity.vo.query.SysLogSysOperateQuery;
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
    private RedisCacheOps redisCacheOps;

    @Override
    public LanaPage<SysLogOperateResult> page(SysLogOperateQuery query) {
        UserDetail user = SecurityUser.getUser();
        query.setUserId(user.getId());
        IPage<SysLogOperateResult> page = baseMapper.getListsTd(getPage(query),query);
        return new LanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

    @Override
    public LanaPage<SysLogOperateResult> sysPage(SysLogSysOperateQuery query) {
        IPage<SysLogOperateResult> page = null;
        //系统日志
        if(query.getOperateType()== OperateTypeEnum.SYSTEM.getValue()){
             page = baseMapper.getSysLogListsTd(getPage(query),query);
        //设备日志
        } else if (query.getOperateType()== OperateTypeEnum.DEVICE.getValue()) {
             page = baseMapper.getdeviceLogListsTd(getPage(query),query);
        //通讯日志
        }else if (query.getOperateType()== OperateTypeEnum.COMMUNICATION.getValue()){
             page = baseMapper.getCommunLogListsTd(getPage(query),query);
        }else {
            page = baseMapper.getSysListsTd(getPage(query),query);
        }

        return new LanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }



    @PostConstruct
    @Scheduled(fixedDelay = 10000)
    // todo 近期将更换为quartz操作
    public void saveLog() {
        if (loggingEnabled) {
            try {
                String key = CacheKeyBuilder.logKey();
                OptLogEntity logEntity = (OptLogEntity) redisCacheOps.rightPop(key);
                if(logEntity != null){
                    StringBuffer sql = new StringBuffer();
                    sql.append("INSERT INTO");
                    // 每次插入10条
                    int count = 10;
                    for (int i = 0; i < count; i++) {
                        OptLogEntity logs = null;
                        if(logEntity!=null){
                            logs = logEntity;
                            logEntity = null;
                        }else {
                            logs = (OptLogEntity) redisCacheOps.rightPop(key);
                        }
                        if (logs != null) {
                            sql.append(" user");
                            sql.append(logs.getUserId());
                            sql.append(" USING sys_log_operate TAGS(");
                            sql.append(logs.getUserId());
                            sql.append(") values(");
                            sql.append("'");
                            sql.append(LocalDateTime.now());
                            sql.append("','");
                            sql.append(logs.getModule());
                            sql.append("','");
                            sql.append(logs.getName());
                            sql.append("','");
                            sql.append(logs.getReqUri());
                            sql.append("','");
                            sql.append(logs.getReqMethod());
                            sql.append("','");
                            sql.append(logs.getReqParams());
                            sql.append("','");
                            sql.append(logs.getIp());
                            sql.append("','");
                            sql.append(logs.getUserAgent());
                            sql.append("',");
                            sql.append(logs.getOperateType());
                            sql.append(",");
                            sql.append("1");
                            sql.append(",'");
                            sql.append(logs.getRealName());
                            sql.append("')");
                        }
                    }
                    if(sql.length()>20){
                        baseMapper.insertTd(sql.toString());
                    }
                }else {
                    return;
                }
            } catch (Exception e) {
                log.error("日志存储失败", e);
            }
        }
    }
}
