package com.adwyxx.springboot.run;

import com.adwyxx.springboot.event.MyApplicationEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 自定义ApplicationEvent运行监听器 {@link SpringApplicationRunListener}
 *
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/10/28 17:32
 */
public class MyApplicationEventRunListener implements SpringApplicationRunListener, Ordered {
    private final SpringApplication application;

    private final String[] args;

    private final SimpleApplicationEventMulticaster initialMulticaster;

    /**
     * 构造函数：注意必须用有包含application和args参数的构造函数
     * @param application: SpringApplication对象实例
     * @param args: 运行参数列表
     */
    public MyApplicationEventRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
        this.initialMulticaster = new SimpleApplicationEventMulticaster();
        for (ApplicationListener<?> listener : application.getListeners()) {
            this.initialMulticaster.addApplicationListener(listener);
        }
    }

    @Override
    public void starting() {
        System.out.println("MyApplicationEventRunListener staring...");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    /**
     * running方法启动时 加载对应的Event
     * @param context:
     */
    @Override
    public void running(ConfigurableApplicationContext context) {
        context.publishEvent(new MyApplicationEvent(this.application, context));
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
