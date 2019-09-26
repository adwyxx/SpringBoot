package com.adwyxx.springboot.Respository;

import com.adwyxx.springboot.annotation.FirstRepository;

/**
 * {@link FirstRepository} 实现类，value="firstRepository" 为Bean名称
 * 在BootStrap中可以使用上下文根据Bean名称获取该Bean类实例
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-26 15:02
 */
@FirstRepository(value="firstRepository")
public class MyFirstRepository {

    public void hello(){
        System.out.println("MyFirstRepository bean say hello!");
    }
}
