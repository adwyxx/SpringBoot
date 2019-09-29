package com.adwyxx.springboot.autoconfig;

import com.adwyxx.springboot.service.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 其他配置
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-27 17:27
 */
@Configuration
public class OtherConfigure {
    @Bean
    public MyService myService(){
        return  new MyService();
    }

}
