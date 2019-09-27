package com.adwyxx.springboot.autoconfig;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 自动装配引导类，要有@EnableAutoConfiguration 注解
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-27 17:34
 */
@EnableAutoConfiguration
public class AutoConfigSpringBoot {
    public static void main(String[] args) {
        //1.初始化一个可配置的应用上下文实例，并启动运行
        ConfigurableApplicationContext context = new SpringApplicationBuilder(AutoConfigSpringBoot.class)
                .web(WebApplicationType.NONE) //不使用web
                .run(args);

        //2.获取所有装配的 Bean 并打印
        String[] beans = context.getBeanDefinitionNames();
        for (int i = 0; i < beans.length; i++) {
            System.out.println(beans[i]);
        }

        //3.关闭上下文
        context.close();
    }
}
