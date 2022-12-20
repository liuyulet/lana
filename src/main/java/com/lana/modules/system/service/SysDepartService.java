package com.lana.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.modules.system.pojo.dto.UserForDepartDTO;
import com.lana.modules.system.pojo.entity.SysDepart;

import java.util.List;


/**
 * 组织机构表(SysDepart)表服务接口
 *
 * @author liuyulet
 * @since 2022-09-29 10:02:49
 */
public interface SysDepartService extends IService<SysDepart> {

    void userForDepart(UserForDepartDTO userForDepartDTO);
}

