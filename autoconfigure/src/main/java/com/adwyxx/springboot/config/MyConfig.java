package com.adwyxx.springboot.config;

import com.adwyxx.springboot.annotation.EnableMyConfig;
import org.springframework.context.annotation.Bean;

/**
 * 自定义配置类，用于{@link EnableMyConfig} 导入该类。
 * 可以实现各种不同的@Bean，从而实现一组功能注入。
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-26 15:47
 */
public class MyConfig {

    public void config(){
        System.out.println("MyConfig do configuration things");
    }

    /**
     * 实现一个@Bean，实现MyConfigBean的功能。@Bean的名称即为方法名
     */
    @Bean
    public MyConfigBean myConfigBean(){
        return new MyConfigBean();
    }
}
