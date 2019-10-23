package com.adwyxx.springboot.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * MyApplicationContextInitializer {@link ApplicationContextInitializer}
 *  自定义一个ApplicationContextInitializer，通过{@link Order}注解的方式设置其SpringFactoryLoader的加载排序：Ordered.HIGHEST_PRECEDENCE+10 {@link Ordered}
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/10/23 16:34
 */
@Order(Ordered.HIGHEST_PRECEDENCE+10)
public class MyApplicationContextInitializer<C extends ConfigurableApplicationContext> implements ApplicationContextInitializer<C> {

    @Override
    public void initialize(C applicationContext) {
        System.out.println("MyApplicationContextInitializer.id"+applicationContext.getId());
    }
}
