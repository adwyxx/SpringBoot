package com.adwyxx.springboot.annotation;

import com.adwyxx.springboot.config.MyConfig;
import com.adwyxx.springboot.config.MyConfigBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义@Enable装配类，导入配置类{@link MyConfig}，而MyConfig类中又实现了@Ben : {@link MyConfigBean}
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-26 15:49
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyConfig.class)
public  @interface EnableMyConfig {

}
