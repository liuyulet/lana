package com.lana.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.modules.system.pojo.entity.BusBindingEntity;


/**
 * 任务—流程绑定(BusBinding)表服务接口
 *
 * @author lyl
 * @since 2022-12-07 21:26:49
 */
public interface BusBindingService extends IService<BusBindingEntity> {

    void startApproval(BusBindingEntity busBinding);

    void deletBinDing(Long itemDd);
}

