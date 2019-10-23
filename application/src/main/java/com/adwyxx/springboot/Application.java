package com.adwyxx.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

/**
 * SpringBootApplication
 */
@SpringBootApplication
public class Application
{
    public static void main( String[] args )
    {
        //应用上下文，在run()方法后返回
        ConfigurableApplicationContext context;
        //1.基本应用
        //context = SpringApplication.run(App.class, args);


        //2.使用SpringApplication的API：可以对Application做更多的自定义设置
        SpringApplication application = new SpringApplication(Application.class);
        //初始化配置Bean源sources
        Set<String> sources = new HashSet<>();
        sources.add(ApplicationConfig.class.getName());
        application.setSources(sources); //设置配置Bean源信息，Bean源可以是Spring配置类、XML文件等
        application.setWebApplicationType(WebApplicationType.NONE); //自定义web应用类型：NONE，SERVLET，REACTIVE
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.setAdditionalProfiles("dev"); //自定义运行时的环境变量
        application.setHeadless(false);
        context = application.run(args); //运行
        /*
        //3.使用SpringApplicationBuilder API（Fluent Builder API 流畅的Builder API,可以链式书写），其配置项和SpringApplication API一致
        context = new SpringApplicationBuilder(App.class)
                .web(WebApplicationType.SERVLET) //可以自定义Web Application Type
                .run(); //运行
        */

        //应用上线文：可以在程序运行后获取Bean信息等
        //context.getBean("applicationBean");
        //context.getBean(ApplicationConfig.class);
    }

    //自定义一个SpringBootApplication配置类
    @SpringBootApplication
    private static class ApplicationConfig{

        @Bean
        public String applicationBean(){
            return "ApplicationConfig.applicationBean";
        }
    }
}
