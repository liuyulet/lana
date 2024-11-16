package com.lana.base.operatelog.service;

import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.CacheOps;
import com.lana.base.operatelog.entity.OptLogEntity;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**操作日志服务
 * @auther liuyulet
 * @date 2024/3/16 14:27
 */
@Service
@AllArgsConstructor
public class OperateLogService {
    private final CacheOps cacheOps;

    @Async
    public void saveLog(OptLogEntity log) {
        String key = CacheKeyBuilder.logKey();
        cacheOps.leftPush(key, log, CacheOps.NO_EXPIRE);
    }
}
