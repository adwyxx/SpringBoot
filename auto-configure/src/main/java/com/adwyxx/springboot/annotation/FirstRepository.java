package com.adwyxx.springboot.annotation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解 @FirstRepository “继承”了 {@link Repository}
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-26 14:44
 * 1. @Component 注解的"继承性" 和 层次性
 *    {@link Component}
 *          |-- {@link Repository}
 *              |--{@link FirstRepository}
 * 2. @ComponentScan对@Component注解类进行装配，而@Repository“继承”了@Component所有可以进行装配。
 *    同理 @FirstRepository “继承”了 @Repository 也可以进行装配
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repository
public @interface FirstRepository {
    //定义一个属性value,默认值 ""
    String value() default "";
}
