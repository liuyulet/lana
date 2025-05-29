package com.lana.system.job;

import cn.hutool.core.date.DateUtil;
import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.RedisCacheOps;
import com.lana.base.operatelog.entity.OptLogEntity;
import com.lana.system.dao.SysLogOperateDao;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author liuyulet
 * @create 2025/5/29 15:35
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class LogOperateJob implements Job {

    @Value("${lana.logging-enabled:false}")
    private boolean loggingEnabled;


    @Value("${lana.log-save-speed:20}")
    private int dataNum;

    @Resource
    private RedisCacheOps redisCacheOps;

    @Resource
    private SysLogOperateDao sysLogOperateDao;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
       /* log.info("\n\t----------------------------------------------------------------------------\n\t" +
                        "=========================定时任务调度，执行业务逻辑====================:\n\t" +
                        "jobName:{}\n\t" +
                        "jobGroup:{}\n\t" +
                        "triggerName:{}\n\t" +
                        "triggerGroup:{}\n\t" +
                        "上次触发时间:{}\n\t" +
                        "本次触发时间:{}\n\t" +
                        "下次触发时间:{}\n\t" +
                        "调度时间:{}\n\t" +
                        "本次出发内容:{}\n\t" +
                        "----------------------------------------------------------------------------",
                context.getJobDetail().getKey().getName(),
                context.getJobDetail().getKey().getGroup(),
                context.getTrigger().getKey().getName(),
                context.getTrigger().getKey().getGroup(),
                DateUtil.formatDateTime(context.getPreviousFireTime()),
                DateUtil.formatDateTime(context.getFireTime()),
                DateUtil.formatDateTime(context.getNextFireTime()),
                DateUtil.formatDateTime(context.getScheduledFireTime()),
                "操作日志定时触发存储。"
        );*/

        if (loggingEnabled) {
            try {
                String key = CacheKeyBuilder.logKey();
                OptLogEntity logEntity = (OptLogEntity) redisCacheOps.rightPop(key);
                if(logEntity != null){
                    StringBuffer sql = new StringBuffer();
                    sql.append("INSERT INTO");
                    // 每次插入10条
                    for (int i = 0; i < dataNum; i++) {
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
                        sysLogOperateDao.insertTd(sql.toString());
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
