package com.adwyxx.springboot.listener;

import com.adwyxx.springboot.event.MyApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

/**
 * 自定义ApplicationListener {@link ApplicationListener}
 *
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/10/28 17:07
 */
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> , Ordered {

    public MyApplicationListener() {
    }

    /**
     * 重写 ApplicationListener 接口方法
     * @author: Leo.Wang, adwyxx@qq.com
     * @param event: 自定义的ApplicationEvent对象
     */
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {

    }

    /**
     * 重写 Ordered 接口方法
     * @author: Leo.Wang, adwyxx@qq.com
     * @return int: SpringFactoryLoad加载排序
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
