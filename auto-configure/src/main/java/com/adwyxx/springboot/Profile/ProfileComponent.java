package com.adwyxx.springboot.Profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * 使用@Profile()注解 组件类，来指定该组件在什么环境下装载
 * 同理：在由@Component派生的注解同样可以和@Profile一起使用。如@Service、@Configuration等
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-27 15:42
 */
@Component
@Profile("dev") //只有配置为：spring.profiles.active=dev 时，才装载该组件
public class ProfileComponent {

    public void hello() {
        System.out.println("ProfileComponent Say hello!!!");
    }
}
