package com.adwyxx.springboot.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * 条件判断业务逻辑实现，实现注解接口{@link Condition}
 * {@link Condition} 接口被注解为 {@link FunctionalInterface},是一个函数是接口
 *      @FunctionalInterface
 *      public interface Condition {
 *          boolean matches(ConditionContext var1, AnnotatedTypeMetadata var2);
 *      }
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-27 10:42
 */
public class OnMyCondition implements Condition {
    private static final Logger logger = LoggerFactory.getLogger(OnMyCondition.class);
    private final String TARGET_VALUE = "Godzilla";
    /**
     * 实现Condition接口方法，根据#条件上下文#和#注解类型元数据#中配置的属性值来进行条件是否匹配判断
     * @param conditionContext: 条件上下文
     * @param annotatedTypeMetadata: 注解类型元数据，即：被@Conditional(OnMyCondition.class)注解的【注解类】的实例的元数据。注意这是个【注解类】的元数据
     * @return boolean : 注解类型元数据中的属性值是否和条件上下文中匹配
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        logger.info("-----------Condition Begin-----------");
        boolean match = false;
        //获取注解类信息
        Map<String,Object> attributes = annotatedTypeMetadata.getAnnotationAttributes(ConditionalOnMyCondition.class.getName());
        String name = String.valueOf(attributes.get("name"));
        match = "Godzilla".equals(name);
        logger.info("ConditionalOnMyCondition.name :"+name+",TargetValue:"+TARGET_VALUE);
        logger.info("-----------Condition End-----------");
        //属性设置为 Godzilla 的Bean 才能被创建
        return match;
    }
}
