package com.adwyxx.springboot.Profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * {@link Profile}修饰方法：满足环境变量的@Bean才会被创建
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-27 15:46
 */
@Configuration
public class ProfileConfigure {
    /**
     * 只有配置为：spring.profiles.active=dev 时，才装载该Bean
     */
    @Bean
    @Profile("dev")
    public String profileBean(){
       return  "ProfileConfigure中被@Profile(\"dev\")注解的Bean : profileBean";
    }

    @Bean
    @Profile("prod")
    public String withoutProfileBean(){
        return  "ProfileConfigure中没有@Profile注解的Bean : withoutProfileBean";
    }
}
