package com.adwyxx.springboot.config;

/**
 * 自定义一个类，用于{@link MyConfig}中实现不同的Bean
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-26 16:09
 */
public class MyConfigBean {
    public void print(){
        System.out.println("MyConfigBean print something...");
    }
}
