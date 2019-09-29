package com.adwyxx.springboot.autoconfig;

import com.adwyxx.springboot.annotation.EnableImportSelector;
import com.adwyxx.springboot.annotation.EnableMyConfig;
import com.adwyxx.springboot.annotation.SelectorBeanModel;
import com.adwyxx.springboot.condition.ConditionalOnMyCondition;
import com.adwyxx.springboot.condition.WindowsOSCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义自动装配类，可以有多重方式装配Bean
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-27 17:26
 */
@Configuration //定义该类为配置装配类
@EnableMyConfig //加入@Enable装配
@EnableImportSelector(model= SelectorBeanModel.SECOND )  //加入Import装配
public class MyAutoConfigure {
    //加入条件装配
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
}
