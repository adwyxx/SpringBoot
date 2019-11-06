package com.adwyxx.springaop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

/**
 * CGLib {@Enhance} FixedValue demo
 * @author: Leo.Wang,adwyxx@qq.com
 * @date: 2019/11/6 22:07
 */
public class FixedValueDemo {

    public void doSomething(){
        System.out.println("do things...");
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FixedValueDemo.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello World!";
            }
        });

        FixedValueDemo demo = (FixedValueDemo) enhancer.create();
        demo.doSomething();
        System.out.println(demo.toString());
        System.out.println(demo.getClass());
        System.out.println(demo.hashCode());
    }
}
