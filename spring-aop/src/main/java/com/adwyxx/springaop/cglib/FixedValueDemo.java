package com.adwyxx.springaop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

/**
 * CGLib {@Enhance} {@FixedValue}: 拦截待返回值得方法,锁定返回值，使得返回值始终是FixedValue#loadObject方法中返回的固定值
 * 1. 重写FixedValue#getObject()方法，被拦截的方法返回值类型必须和getObject的返回值类型相同
 * 2. 被拦截的方法不能是fianl的
 * @author: Leo.Wang,adwyxx@qq.com
 * @date: 2019/11/6 22:07
 */
public class FixedValueDemo {
    //定义被FixedValue拦截的方法,返回String类型
    public String doSomething(String arg){
        System.out.println("do things...");
        return "doSomething:"+arg;
    }
    //final修饰的方法不能被FixedValue拦截
    public final String finalMethod(){
        return "final method...";
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FixedValueDemo.class);
        //FixedValue用于拦截有返回值的方法
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello World!";
            }
        });
        //enhancer.create()创建增强对象
        FixedValueDemo demo = (FixedValueDemo) enhancer.create();
        //doSomething()被拦截，输出:Hello World!
        System.out.println(demo.doSomething("call method"));
        System.out.println(demo.toString()); //同上
        System.out.println(demo.finalMethod());//输出：final method... FixedValue不能拦截final修饰的方法
        System.out.println(demo.getClass()); //不能够拦截，Object.getClass方法是final的
        //将报错，因为hashCode返回int类型，与FixedValue.loadObject返回值类型不同
        System.out.println(demo.hashCode());
    }
}
