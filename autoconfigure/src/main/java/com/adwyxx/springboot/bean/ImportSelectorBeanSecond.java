package com.adwyxx.springboot.bean;

import com.adwyxx.springboot.annotation.MyImportSelector;

/**
 * 一个Bean类，用于{@link MyImportSelector} 返回Bean
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-26 17:10
 */
public class ImportSelectorBeanSecond {

    public void doWork(){
        System.out.println("This is ImportSelectorBeanSecond working...");
    }
}
