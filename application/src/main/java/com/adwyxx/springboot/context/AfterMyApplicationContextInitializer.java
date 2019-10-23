package com.adwyxx.springboot.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

/**
 *  AfterMyApplicationContextInitializer {@link ApplicationContextInitializer}
 *  自定义一个ApplicationContextInitializer，通过实现{@link Ordered}接口的方式设置其SpringFactoryLoader的加载排序：Ordered.HIGHEST_PRECEDENCE+20
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/10/23 16:41
 */
public class AfterMyApplicationContextInitializer<C extends ConfigurableApplicationContext> implements ApplicationContextInitializer<C>, Ordered {
    //设置默认的SpringFactoryLoader加载顺序码，低于MyApplicationContextInitializer
    private final int DEFAULT_ORDER_NUMBER = Ordered.HIGHEST_PRECEDENCE+20;

    @Override
    public void initialize(C applicationContext) {
        System.out.println("AfterMyApplicationContextInitializer.id"+applicationContext.getId());
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER_NUMBER;
    }
}