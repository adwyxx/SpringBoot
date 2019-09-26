package com.adwyxx.springboot.bootstrap;

import com.adwyxx.springboot.Respository.MyFirstRepository;
import com.adwyxx.springboot.annotation.EnableImportSelector;
import com.adwyxx.springboot.annotation.SelectorBeanModel;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * EnableImportSelector 引导类
 *
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-26 17:58
 */
@EnableImportSelector(model= SelectorBeanModel.SECOND )
public class EnableImportSelectorBootStrap {
    public static void main(String[] args) {
        //1.初始化一个可配置的应用上下文实例，并启动运行
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableImportSelectorBootStrap.class)
                .web(WebApplicationType.NONE) //不使用web
                .run(args);

        String[] beans = context.getBeanDefinitionNames();
        for (int i = 0; i < beans.length; i++) {
            System.out.println(beans[i]);
        }
        //3.关闭上下文
        context.close();
    }
}
