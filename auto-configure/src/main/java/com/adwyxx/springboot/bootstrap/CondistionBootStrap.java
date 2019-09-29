package com.adwyxx.springboot.bootstrap;

import com.adwyxx.springboot.condition.ConditionalOnMyCondition;
import com.adwyxx.springboot.condition.WindowsOSCondition;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * 条件装配引导类
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-27 10:31
 */
public class CondistionBootStrap {

    /**
     * 声明一个Bean，添加条件注解{@link ConditionalOnMyCondition}，符合条件则被创建，否则不会被创建
     * @return String: 一个String类型的Bean
     */
    @Bean
    @ConditionalOnMyCondition(name="Godzilla")
    public String godzillaBean(){
        System.out.println("符合条件，创建godzillaBean");
        return "The awesome Godzilla";
    }

    /**
     * 直接使用 @Conditional 注解，判断当前程序运行的系统平台
     * @return String: 根据系统平台判断是否创建Bean
     */
    @Bean
    @Conditional(WindowsOSCondition.class)
    public String windowsOSBean(){
        return "Windows System Operation";
    }

    public static void main(String[] args) {
    //1.初始化一个可配置的应用上下文实例，并启动运行
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CondistionBootStrap.class)
                .web(WebApplicationType.NONE) //不使用web
                .run(args);

        //2.获取所有装配的 Bean
        String godzilla = (String)context.getBean("godzillaBean");
        System.out.println(godzilla);

        String windowsOSBean = (String)context.getBean("windowsOSBean");
        System.out.println(windowsOSBean);
        //3.关闭上下文
        context.close();
    }
}
