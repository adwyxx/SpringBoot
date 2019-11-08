package com.adwyxx.springaop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 接口生成器 {@link InterfaceMaker} : 会动态生成一个接口，该接口包含指定类定义的所有方法
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/8 15:17
 */
public class InterfaceMakerDemo {

    public String getValue(){
        return "value";
    }

    public void setId(int id){
        System.out.println(id);
    }
    //注意：私有的方法不会被InterfaceMaker创建到接口中
    private void privateMethod(){
        System.out.println("privateMethod");
    }
    public final void finalMathod(){
        System.out.println("finalMathod");
    }
    public static void staticMethod(){
        System.out.println("staticMethod");
    }

    public static void main(String[] args) throws NoSuchMethodException,
            InvocationTargetException,
            IllegalAccessException
    {
        //1.创建InterfaceMaker对象
        InterfaceMaker interfaceMaker = new InterfaceMaker();
        //2.给InterfaceMaker对象添加目标类的Class对象，
        interfaceMaker.add(InterfaceMakerDemo.class);
        //3.创建接口Class对象，该接口包含上述添加的类的所有public方法声明
        Class<?> interfaceClazz = interfaceMaker.create();
        for(Method method : interfaceClazz.getMethods()){
            System.out.println(method.getName());
        }
        System.out.println("----------------------------------");
        //4.接口代理，并给代理方法添加方法拦截
        Object proxy = Enhancer.create(Object.class, new Class[]{interfaceClazz}, new MethodInterceptor() {
            //由于是代理的接口，并没有方法体。所以拦截方法后需要实现具体操作、
            //而不能调用methodProxy.invokeSuper(obj,parames),此时obj是一个Object对象，并没有对应的方法，将会抛出NoSuchMehtod异常
            @Override
            public Object intercept(Object obj, Method method, Object[] parames, MethodProxy methodProxy) throws Throwable {
                System.out.println("before...");

                if(method.getName().equals("getValue"))
                {
                    System.out.println("getValue do something...");
                } else if(method.getName().equals("staticMethod")){
                    System.out.println("static method do something...");
                } else {
                    System.out.println("invoke method: "+method.getName());
                }

                System.out.println("after");
                return method.getName();
            }
        });
        //5.通过反射调用代理对象方法
        Method method = proxy.getClass().getMethod("setId",new Class[]{int.class});
        method.invoke(proxy,new Object[]{111});

        Method staticMethod = proxy.getClass().getMethod("staticMethod");
        staticMethod.invoke(proxy);
    }
}
