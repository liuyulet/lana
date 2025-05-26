package com.lana.abutment.mqtthandle.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.abutment.mqtthandle.entity.vo.push.DirectivesSend;
import com.lana.abutment.mqtthandle.servepublish.MessagePublish;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.result.LanaResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/11/22 16:06
 */
@RestController
@RequestMapping("/abutment/directives")
@Tag(name = "指令推送")
@ApiSupport(author = "liuyulet")
public class DirectivesController {

    @Resource
    private MessagePublish messagePublish;

    @PostMapping("/pushData")
    @Operation(summary = "指令发送")
    @OptLog(type = OperateTypeEnum.COMMAND)
    public LanaResult<DirectivesSend> pushData(@RequestBody DirectivesSend directivesSend) {
        messagePublish.publishTopic(directivesSend.getTopic(), directivesSend.getPushData());
        return LanaResult.ok(directivesSend);
    }
}
