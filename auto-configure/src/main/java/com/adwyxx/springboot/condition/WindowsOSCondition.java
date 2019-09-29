package com.adwyxx.springboot.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 系统环境判断条件,
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-27 14:18
 */
public class WindowsOSCondition implements Condition {
    private static final Logger logger = LoggerFactory.getLogger(WindowsOSCondition.class);
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        logger.info("---------WindowsOSCondition Begin--------");
        //使用上下文的getEnvironment()获取当前程序运行的操作系统环境信息
        String os = conditionContext.getEnvironment().getProperty("os.name");
        System.out.println(os);
        logger.info("---------WindowsOSCondition End--------");
        return os.contains("Windows");
    }
}
