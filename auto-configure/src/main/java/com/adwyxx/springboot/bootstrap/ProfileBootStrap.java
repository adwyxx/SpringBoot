package com.adwyxx.springboot.bootstrap;

import com.adwyxx.springboot.Profile.ProfileAnnotation;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Profile装配引导类，只有在环境变量为dev时才会装配对应的Bean
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-27 15:40
 */
@ProfileAnnotation("Profile Annotation Value")
@ComponentScan(basePackages = "com.adwyxx.springboot.Profile")
public class ProfileBootStrap {

    public static void main(String[] args) {
        //1.初始化一个可配置的应用上下文实例，并启动运行
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ProfileBootStrap.class)
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
