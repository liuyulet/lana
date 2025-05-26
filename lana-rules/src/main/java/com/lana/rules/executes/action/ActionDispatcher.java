package com.lana.rules.executes.action;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

/**
 * 统一调度规则触发后的动作：
 * @author Liuyulet
 * @version 1.0
 * @data 2025/5/17 22:30
 */
@Component
public class ActionDispatcher {

    @Resource(name = "ruleActionTaskExecutor")
    private ExecutorService ruleActionExecutor;

    public void dispatch(Runnable action) {
        ruleActionExecutor.submit(() -> {
            try {
                action.run();
            } catch (Exception e) {
                System.out.println("规则动作执行失败" + e);
            }
        });
    }

}
