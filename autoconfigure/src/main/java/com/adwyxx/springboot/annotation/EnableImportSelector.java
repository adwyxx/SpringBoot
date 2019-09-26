package com.adwyxx.springboot.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO..
 *
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-26 17:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Import(MyImportSelector.class)
public @interface EnableImportSelector {

    //选择Bean的类型
    SelectorBeanModel model() default SelectorBeanModel.FIRST;
}
