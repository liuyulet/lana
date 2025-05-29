package com.lana.system.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @auther liuyulet
 * @date 2024/3/30 17:14
 */
@Slf4j
@Service
public class SysLogOperateServiceImpl extends BaseServiceImpl<SysLogOperateDao, SysLogOperateEntity> implements SysLogOperateService {


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

}
