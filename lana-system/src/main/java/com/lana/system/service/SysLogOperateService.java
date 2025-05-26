package com.lana.system.service;

import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.mybatis.service.BaseService;
import com.lana.system.entity.SysLogOperateEntity;
import com.lana.system.entity.vo.query.SysLogOperateQuery;
import com.lana.system.entity.vo.query.SysLogSysOperateQuery;
import com.lana.system.entity.vo.result.SysLogOperateResult;

public interface SysLogOperateService extends BaseService<SysLogOperateEntity> {

    LanaPage<SysLogOperateResult> page(SysLogOperateQuery query);

    LanaPage<SysLogOperateResult> sysPage(SysLogSysOperateQuery query);
}
