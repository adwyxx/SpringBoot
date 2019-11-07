package com.adwyxx.springaop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;

/**
 * CGlib {@link InvocationHandler} demo
 *
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019/11/7 21:30
 * ① Object proxy：代理对象引用（通过enhancer.create()创建的对象）——谨慎使用，防止无限递归而陷入死循环。
 * ② Method method：代理类中接受的接口方法。
 * ③ Object[] args：向代理对象的方法中传递的参数。
 * 例如：proxy.doSomething(strings); 中proxy即代理对象；doSomething()即代理类中接受的接口方法；strings即传递的参数。
 * 为避免使用invoke出现的死循环一般使用{@link MethodInterceptor} 接口：
 * MethodInterceptor比InvocationHandler多一个参数MethodProxy（cglib生成用来代替Method对象的一个对象）
 * 使用MethodProxy比调用JDK自身的Method直接执行方法效率会有提升
 */
public class InvocationHandlerDemo {

    //被增强的方法
    public String doSomething(String arg){
        return  "do some thing..."+arg;
    }

    public static void main(String[] args) {
        //初始化增强处理类
        Enhancer enhancer = new Enhancer();
        //设置被代理的类
        enhancer.setSuperclass(InvocationHandlerDemo.class);
        //设置代理类的增强处理
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
                //必须注意死循环。invoke中调用的任何原代理类方法，均会重新代理到invoke方法中
                if(method.getDeclaringClass() != Object.class && method.getReturnType()==String.class)
                {
                    System.out.println("before");
                    Object result = method.invoke(proxy,arguments);
                    System.out.println("after");
                    return result;
                }else{
                    //为防止死循环，抛出异常
                    throw new RuntimeException("Do not know what to do");
                }
            }
        });
        //创建代理类实例
        InvocationHandlerDemo demo = (InvocationHandlerDemo)enhancer.create();
        System.out.println(demo.doSomething("Hello World"));
    }
}
