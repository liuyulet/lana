package com.lana.modules.system.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lana.common.utils.PageUtils;
import com.lana.modules.system.pojo.entity.SysDeman;
import com.lana.modules.system.pojo.entity.SysStep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SysStep)表服务接口
 *
 * @author liuyulet
 * @since 2022-10-22 14:31:45
 */
public interface SysStepService  extends IService<SysStep> {


    PageUtils queryPage(Map<String, Object> params);

    void saveSysStep(SysStep sysStep);

    void savess(String value, int[] users, Long stepId);

    JSONObject getByStep(Long stepId);

    void delStepNode(Long stepId);

    void delStepNodeUser(Long stepId);

    List<Long> getUserId(Object stepId, Object stepNode);
}
