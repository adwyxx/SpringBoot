package com.adwyxx.springboot.bootstrap;

import com.adwyxx.springboot.Respository.MyFirstRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 自定义引导类
 * 指定{@link ComponentScan} 的basePackages属性，指定扫描装配类的包
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-26 15:07
 */
@ComponentScan(basePackages = "com.adwyxx.springboot.Respository")
public class RepositoryBootStrap {
    public static void main(String[] args) {
        //1.初始化一个可配置的应用上下文实例，并启动运行
        ConfigurableApplicationContext context = new SpringApplicationBuilder(RepositoryBootStrap.class)
                .web(WebApplicationType.NONE) //不使用web
                .run(args);

        //2.通过上下文的getBean方法可以获取装配的Bean实例
        MyFirstRepository repository = context.getBean("firstRepository", MyFirstRepository.class);
        if(repository !=null)
        {
            System.out.println(repository);
            repository.hello();
        }

        //3.关闭上下文
        context.close();
    }
}
