package com.lana.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Result;
import com.lana.modules.system.pojo.dto.OverTeskDTO;
import com.lana.modules.system.pojo.entity.SysDemanUserEntity;
import com.lana.modules.system.pojo.entity.SysUserEntity;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SysDemanUser)表服务接口
 * @auther liuyulet
 * @since 2022-10-07 23:16:36
 */
public interface SysDemanUserService extends IService<SysDemanUserEntity> {


    List<HashMap<String, Object>> getAllOver(String demanId);

    PageUtils queryPage(Map<String, String> params, SysUserEntity userEntity);

    List<SysDemanUserEntity> selectdata(Long palnItemId);

    Result overTask(OverTeskDTO overTeskDTO);
}
