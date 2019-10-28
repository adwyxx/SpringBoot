package com.adwyxx.springboot.event;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 自定义ApplicationEvent {@link ApplicationEvent}
 *
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/10/28 17:03
 */
public class MyApplicationEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1177167243056908376L;
    private ConfigurableApplicationContext context;

    /**
     * Create a new {@link MyApplicationEvent} instance.
     * @param application the current application
     * @param context the context that was being created
     */
    public MyApplicationEvent(SpringApplication application,ConfigurableApplicationContext context) {
        super(application);
        this.context = context;
    }

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyApplicationEvent(Object source) {
        super(source);
        System.out.println("==============MyApplicationEvent created!==============");
    }
}
