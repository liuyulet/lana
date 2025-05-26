package com.lana.rules.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.rules.service.RulesItemNodeService;
import com.lana.rules.service.RulesItemQuratzService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuyulet
 * @create 2025/3/4 15:58
 */

@RestController
@RequestMapping("/rules/rulesItemQuratz")
@Tag(name = "规则实例")
@ApiSupport(author = "liuyulet")
public class RulesItemQuratzController {
    @Resource
    private RulesItemQuratzService rulesItemQuratzService;


}
