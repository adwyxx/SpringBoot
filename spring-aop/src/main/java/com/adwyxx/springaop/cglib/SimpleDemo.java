package com.adwyxx.springaop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * CGLib {@Enhance}
 * @author: Leo.Wang,adwyxx@qq.com
 * @date: 2019/11/6 22:07
 * Enhancer类和JDK动态代理的Proxy类相似
 * 不同的是：Enhancer既能够代理普通的class，也能够代理接口（Proxy只能代理接口）
 * Enhancer不能够拦截final方法
 * Enhancer也不能对fianl类进行代理操作。这也是Hibernate为什么不能持久化final class的原因。
 */
public class SimpleDemo {

    public void doSomething(){
        System.out.println("do some thing...");
    }

    public static void main(String[] args) {
        //初始化增强处理类
        Enhancer enhancer = new Enhancer();
        //设置被代理的类
        enhancer.setSuperclass(SimpleDemo.class);
        //设置代理类的增强处理
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("Before");
                Object result = methodProxy.invokeSuper(obj,args);
                System.out.println("After");
                return result;
            }
        });
        //创建代理类实例
        SimpleDemo demo = (SimpleDemo)enhancer.create();
        demo.doSomething();
    }
}
