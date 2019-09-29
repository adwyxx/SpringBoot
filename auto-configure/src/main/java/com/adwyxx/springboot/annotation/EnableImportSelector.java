package com.adwyxx.springboot.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Enable注解类实现：导入{@link ImportSelector}接口实现类
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-26 17:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyImportSelector.class)
public @interface EnableImportSelector {
    /**
     * 等于属性，可以在{@link ImportSelector} 接口实现类中获取该属性，根据不同的属性值返回不同的 Bean 类集合
     * @return SelectorBeanModel: Bean 类型
     */
    SelectorBeanModel model() default SelectorBeanModel.FIRST;
}
