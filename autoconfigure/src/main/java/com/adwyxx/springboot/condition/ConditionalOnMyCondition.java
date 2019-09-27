package com.adwyxx.springboot.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义Conditional注解类，通过OnMyCondition类来判断配置的属性是否符合条件
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-27 11:32
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnMyCondition.class)
public @interface ConditionalOnMyCondition {
    String name() default "";
}
