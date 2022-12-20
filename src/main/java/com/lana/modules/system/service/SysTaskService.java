package com.lana.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.common.utils.PageUtils;
import com.lana.modules.system.pojo.entity.SysDeman;
import com.lana.modules.system.pojo.entity.SysTaskEntity;
import com.lana.modules.system.pojo.vo.LookCollaVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SysTask)表服务接口
 * @auther liuyulet
 * @since 2022-10-07 21:45:14
 */
public interface SysTaskService extends IService<SysTaskEntity> {


    PageUtils queryPage(Map<String, Object> params);

    List<LookCollaVO> getLookColla(String demanId);
}
