package com.adwyxx.springboot.bootstrap;

import com.adwyxx.springboot.annotation.EnableMyConfig;
import com.adwyxx.springboot.config.MyConfig;
import com.adwyxx.springboot.config.MyConfigBean;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * 自定义@Enable 注解装配 引导类
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-26 15:54
 * 1. {@link EnableMyConfig} 注解{@link Import} 自定义配置类{@link MyConfig}
 */
@ComponentScan(basePackages = "com.adwyxx.springboot.annotation")
@EnableMyConfig
public class EnableMyConfigBootStrap {
    public static void main(String[] args) {
        //1.初始化上下文
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableMyConfigBootStrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        //2.使用上下文context.getBean()方法获取 @EnableMyConfig 装配的Bean
        System.out.println("----------------------------------------------");
        //获取@EnableMyConfig中@Import()的MyConfig
        MyConfig myConfig = context.getBean(MyConfig.class);
        System.out.println(myConfig);
        myConfig.config();

        //获取MyConfig中装配的Bean
        MyConfigBean configBean = context.getBean(MyConfigBean.class);
        System.out.println(configBean);
        configBean.print();
        System.out.println("----------------------------------------------");

        context.close();
    }
}
