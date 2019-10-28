package com.adwyxx.springboot;

import com.adwyxx.springboot.event.MyApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * SpringApplicationEvent启动引导类
 *
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/10/28 17:19
 */
public class SpringApplicationEventBoot {

    public static void main(String[] args) {
        //1. 创建一个应用上下文对象
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApplicationEventBoot.class);

        //2. 注册事件监听
        context.addApplicationListener(event->{
            System.out.println("===========注册事件监听:"+event+"==========");
        });

        //3. 启动上下文
        context.refresh();

        //4. 发布应用事件
        context.publishEvent("2019 event");
        //context.publishEvent(new MyApplicationEvent("Hello world!"));

        //5. 关闭上下文
        context.close();
    }
}
