package com.lana.modules.system.controller;

import com.lana.common.utils.Result;
import com.lana.modules.system.pojo.entity.SysStepNodeUser;
import com.lana.modules.system.service.SysStepNodeUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * (SysStepNodeUser)表控制层
 *
 * @author liuyulet
 * @since 2022-10-22 14:31:45
 */
@RestController
@RequestMapping("/sysStepNodeUser")
public class SysStepNodeUserController  extends  AbstractController{
    /**
     * 服务对象
     */
    @Resource
    private SysStepNodeUserService sysStepNodeUserService;




}

