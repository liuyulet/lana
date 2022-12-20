package com.lana.modules.system.controller;

import com.lana.modules.system.pojo.entity.SysStepNode;
import com.lana.modules.system.service.SysStepNodeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysStepNode)表控制层
 *
 * @author liuyulet
 * @since 2022-10-22 14:31:45
 */
@RestController
@RequestMapping("/sysStepNode")
public class SysStepNodeController extends  AbstractController{
    /**
     * 服务对象
     */
    @Resource
    private SysStepNodeService sysStepNodeService;

}

