package com.adwyxx.springboot.Profile;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用@Profile注解修饰的自定义注解类，只有运行环境和@Profile注解的value相同时注解才生效
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-27 15:55
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Profile("dev")
public @interface ProfileAnnotation {
    String value() default "";
}
